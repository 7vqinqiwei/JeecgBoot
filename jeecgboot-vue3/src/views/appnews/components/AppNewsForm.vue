<template>
  <a-spin :spinning="confirmLoading">
    <JFormContainer :disabled="disabled">
      <template #detail>
        <a-form ref="formRef" class="antd-modal-form" :labelCol="labelCol" :wrapperCol="wrapperCol" name="AppNewsForm">
          <a-row>
						<a-col :span="24">
							<a-form-item label="标题" v-bind="validateInfos.title" id="AppNewsForm-title" name="title">
								<a-input v-model:value="formData.title" placeholder="请输入标题"  allow-clear ></a-input>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="简介" v-bind="validateInfos.introduce" id="AppNewsForm-introduce" name="introduce">
								<a-textarea v-model:value="formData.introduce" :rows="4" placeholder="请输入简介" />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="图片" v-bind="validateInfos.pic" id="AppNewsForm-pic" name="pic">
								<j-image-upload :fileMax="0" v-model:value="formData.pic" ></j-image-upload>
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="发布时间" v-bind="validateInfos.publishTime" id="AppNewsForm-publishTime" name="publishTime">
								<a-date-picker placeholder="请选择发布时间"  v-model:value="formData.publishTime" showTime value-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"  allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="发布人" v-bind="validateInfos.publishBy" id="AppNewsForm-publishBy" name="publishBy">
						<j-select-user v-model:value="formData.publishBy"      allow-clear />
							</a-form-item>
						</a-col>
						<a-col :span="24">
							<a-form-item label="内容" v-bind="validateInfos.content" id="AppNewsForm-content" name="content">
								<j-editor v-model:value="formData.content"  :autoFocus="false"/>
							</a-form-item>
						</a-col>
          </a-row>
        </a-form>
      </template>
    </JFormContainer>
  </a-spin>
</template>

<script lang="ts" setup>
  import { ref, reactive, defineExpose, nextTick, defineProps, computed, onMounted } from 'vue';
  import { defHttp } from '/@/utils/http/axios';
  import { useMessage } from '/@/hooks/web/useMessage';
  import JSelectUser from '/@/components/Form/src/jeecg/components/JSelectUser.vue';
  import JImageUpload from '/@/components/Form/src/jeecg/components/JImageUpload.vue';
  import JEditor from '/@/components/Form/src/jeecg/components/JEditor.vue';
  import { getDateByPicker, getValueType } from '/@/utils';
  import { saveOrUpdate } from '../AppNews.api';
  import { Form } from 'ant-design-vue';
  import JFormContainer from '/@/components/Form/src/container/JFormContainer.vue';
  const props = defineProps({
    formDisabled: { type: Boolean, default: false },
    formData: { type: Object, default: () => ({})},
    formBpm: { type: Boolean, default: true }
  });
  const formRef = ref();
  const useForm = Form.useForm;
  const emit = defineEmits(['register', 'ok']);
  const formData = reactive<Record<string, any>>({
    id: '',
    title: '',
    introduce: '',
    pic: '',
    publishTime: '',
    publishBy: '',
    content: '',
  });
  const { createMessage } = useMessage();
  const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
  const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
  const confirmLoading = ref<boolean>(false);
  //表单验证
  const validatorRules = reactive({
    title: [{ required: true, message: '请输入标题!'},],
    introduce: [{ required: true, message: '请输入简介!'},],
    pic: [{ required: true, message: '请上传图片!'},],
    content: [{ required: true, message: '请输入内容!'},],
  });
  const { resetFields, validate, validateInfos } = useForm(formData, validatorRules, { immediate: false });
  //日期个性化选择
  const fieldPickers = reactive({
  });

  // 表单禁用
  const disabled = computed(()=>{
    if(props.formBpm === true){
      if(props.formData.disabled === false){
        return false;
      }else{
        return true;
      }
    }
    return props.formDisabled;
  });


  /**
   * 新增
   */
  function add() {
    edit({});
  }

  /**
   * 编辑
   */
  function edit(record) {
    nextTick(() => {
      resetFields();
      const tmpData = {};
      Object.keys(formData).forEach((key) => {
        if(record.hasOwnProperty(key)){
          tmpData[key] = record[key]
        }
      })
      //赋值
      Object.assign(formData, tmpData);
    });
  }

  /**
   * 提交数据
   */
  async function submitForm() {
    try {
      // 触发表单验证
      await validate();
    } catch ({ errorFields }) {
      if (errorFields) {
        const firstField = errorFields[0];
        if (firstField) {
          formRef.value.scrollToField(firstField.name, { behavior: 'smooth', block: 'center' });
        }
      }
      return Promise.reject(errorFields);
    }
    confirmLoading.value = true;
    const isUpdate = ref<boolean>(false);
    //时间格式化
    let model = formData;
    if (model.id) {
      isUpdate.value = true;
    }
    //循环数据
    for (let data in model) {
      // 更新个性化日期选择器的值
      model[data] = getDateByPicker(model[data], fieldPickers[data]);
      //如果该数据是数组并且是字符串类型
      if (model[data] instanceof Array) {
        let valueType = getValueType(formRef.value.getProps, data);
        //如果是字符串类型的需要变成以逗号分割的字符串
        if (valueType === 'string') {
          model[data] = model[data].join(',');
        }
      }
    }
    await saveOrUpdate(model, isUpdate.value)
      .then((res) => {
        if (res.success) {
          createMessage.success(res.message);
          emit('ok');
        } else {
          createMessage.warning(res.message);
        }
      })
      .finally(() => {
        confirmLoading.value = false;
      });
  }


  defineExpose({
    add,
    edit,
    submitForm,
  });
</script>

<style lang="less" scoped>
  .antd-modal-form {
    padding: 14px;
  }
</style>
