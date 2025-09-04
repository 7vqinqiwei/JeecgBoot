<template>
  <a-spin :spinning="confirmLoading">
    <JFormContainer :disabled="disabled">
      <template #detail>
        <a-form
          ref="formRef"
          class="antd-modal-form"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          name="AppCaseForm"
        >
          <a-row>
            <!-- ====== 项目介绍 ====== -->
            <a-col :span="24">
              <a-card title="项目介绍" bordered>
                <a-form-item label="案例标题" v-bind="validateInfos.title" name="title">
                  <a-input v-model:value="formData.title" placeholder="请输入案例标题" allow-clear />
                </a-form-item>
                <a-form-item label="案例类型" v-bind="validateInfos.caseType" name="caseType">
                  <j-dict-select-tag v-model:value="formData.caseType" dictCode="caseType" placeholder="请选择案例类型" allow-clear />
                </a-form-item>
                <a-form-item label="项目单位" v-bind="validateInfos.workUnit" name="workUnit">
                  <a-input v-model:value="formData.workUnit" placeholder="请输入项目单位" allow-clear />
                </a-form-item>
                <a-form-item label="Logo" v-bind="validateInfos.logo" name="logo">
                  <j-image-upload :fileMax="0" v-model:value="formData.logo" />
                </a-form-item>
                <a-form-item label="介绍描述" v-bind="validateInfos.introduce" name="introduce">
                  <a-textarea v-model:value="formData.introduce" :rows="4" placeholder="请输入介绍描述" />
                </a-form-item>
<!--                <a-form-item label="案例内容" v-bind="validateInfos.content" name="content">-->
<!--                  <j-editor v-model:value="formData.content" :autoFocus="false" />-->
<!--                </a-form-item>-->
                <a-form-item label="企业痛点" v-bind="validateInfos.painPoint1" name="painPoint1">
                  <a-input v-model:value="formData.painPoint1" placeholder="请输入痛点" allow-clear />
                </a-form-item>
                <a-form-item label=" " v-bind="validateInfos.painPoint2" name="painPoint2">
                  <a-input v-model:value="formData.painPoint2" placeholder="请输入痛点" allow-clear />
                </a-form-item>
                <a-form-item label=" " v-bind="validateInfos.painPoint3" name="painPoint3">
                  <a-input v-model:value="formData.painPoint3" placeholder="请输入痛点" allow-clear />
                </a-form-item>
                <a-form-item label=" " v-bind="validateInfos.painPoint4" name="painPoint4">
                  <a-input v-model:value="formData.painPoint4" placeholder="请输入痛点" allow-clear />
                </a-form-item>
                <a-form-item label="项目图片" v-bind="validateInfos.pic" name="pic">
                  <j-image-upload :fileMax="0" v-model:value="formData.pic" />
                </a-form-item>
              </a-card>
            </a-col>

            <!-- ====== 建设方案 ====== -->
            <a-col :span="24" style="margin-top: 16px">
              <a-card title="建设方案" bordered>
                <a-form-item label="方案一" v-bind="validateInfos.scheme1" name="scheme1">
                  <a-input v-model:value="formData.scheme1" placeholder="请输入方案一" allow-clear />
                </a-form-item>
                <a-form-item label="方案描述" v-bind="validateInfos.scheme1desc" name="scheme1desc">
                  <a-input v-model:value="formData.scheme1desc" placeholder="请输入方案描述" allow-clear />
                </a-form-item>
                <a-form-item label="方案二" v-bind="validateInfos.scheme2" name="scheme2">
                  <a-input v-model:value="formData.scheme2" placeholder="请输入方案二" allow-clear />
                </a-form-item>
                <a-form-item label="方案描述" v-bind="validateInfos.scheme2desc" name="scheme2desc">
                  <a-input v-model:value="formData.scheme2desc" placeholder="请输入方案描述" allow-clear />
                </a-form-item>
                <a-form-item label="方案三" v-bind="validateInfos.scheme3" name="scheme3">
                  <a-input v-model:value="formData.scheme3" placeholder="请输入方案三" allow-clear />
                </a-form-item>
                <a-form-item label="方案描述" v-bind="validateInfos.scheme3desc" name="scheme3desc">
                  <a-input v-model:value="formData.scheme3desc" placeholder="请输入方案描述" allow-clear />
                </a-form-item>
                <a-form-item label="方案四" v-bind="validateInfos.scheme4" name="scheme4">
                  <a-input v-model:value="formData.scheme4" placeholder="请输入方案四" allow-clear />
                </a-form-item>
                <a-form-item label="方案描述" v-bind="validateInfos.scheme4desc" name="scheme4desc">
                  <a-input v-model:value="formData.scheme4desc" placeholder="请输入方案描述" allow-clear />
                </a-form-item>
                <a-form-item label="配图上传" v-bind="validateInfos.schemePic" name="schemePic">
                  <j-image-upload :fileMax="0" v-model:value="formData.schemePic" />
                </a-form-item>
              </a-card>
            </a-col>

            <!-- ====== 上线效果 ====== -->
            <a-col :span="24" style="margin-top: 16px">
              <a-card title="上线效果" bordered>
                <a-form-item label="标题一" v-bind="validateInfos.onlineEffect1" name="onlineEffect1">
                  <a-input v-model:value="formData.onlineEffect1" placeholder="请输入标题一" allow-clear />
                </a-form-item>
                <a-form-item label="描述" v-bind="validateInfos.onlineEffect1Desc" name="onlineEffect1Desc">
                  <a-input v-model:value="formData.onlineEffect1Desc" placeholder="请输入描述" allow-clear />
                </a-form-item>
                <a-form-item label="标题二" v-bind="validateInfos.onlineEffect2" name="onlineEffect2">
                  <a-input v-model:value="formData.onlineEffect2" placeholder="请输入标题二" allow-clear />
                </a-form-item>
                <a-form-item label="描述" v-bind="validateInfos.onlineEffect2Desc" name="onlineEffect2Desc">
                  <a-input v-model:value="formData.onlineEffect2Desc" placeholder="请输入描述" allow-clear />
                </a-form-item>
                <a-form-item label="标题三" v-bind="validateInfos.onlineEffect3" name="onlineEffect3">
                  <a-input v-model:value="formData.onlineEffect3" placeholder="请输入标题三" allow-clear />
                </a-form-item>
                <a-form-item label="描述" v-bind="validateInfos.onlineEffect3Desc" name="onlineEffect3Desc">
                  <a-input v-model:value="formData.onlineEffect3Desc" placeholder="请输入描述" allow-clear />
                </a-form-item>
                <a-form-item label="标题四" v-bind="validateInfos.onlineEffect4" name="onlineEffect4">
                  <a-input v-model:value="formData.onlineEffect4" placeholder="请输入标题四" allow-clear />
                </a-form-item>
                <a-form-item label="描述" v-bind="validateInfos.onlineEffect4Desc" name="onlineEffect4Desc">
                  <a-input v-model:value="formData.onlineEffect4Desc" placeholder="请输入描述" allow-clear />
                </a-form-item>
              </a-card>
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
import JDictSelectTag from '/@/components/Form/src/jeecg/components/JDictSelectTag.vue';
import JImageUpload from '/@/components/Form/src/jeecg/components/JImageUpload.vue';
import JEditor from '/@/components/Form/src/jeecg/components/JEditor.vue';
import { getDateByPicker, getValueType } from '/@/utils';
import { saveOrUpdate } from '../AppCase.api';
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
  caseType: '1',
  workUnit: '',
  logo: '',
  introduce: '',
  content: '',
  painPoint1: '',
  painPoint2: '',
  painPoint3: '',
  painPoint4: '',
  pic: '',
  scheme1: '',
  scheme1desc: '',
  scheme2: '',
  scheme2desc: '',
  scheme3: '',
  scheme3desc: '',
  scheme4: '',
  scheme4desc: '',
  schemePic: '',
  onlineEffect1: '',
  onlineEffect1Desc: '',
  onlineEffect2: '',
  onlineEffect2Desc: '',
  onlineEffect3: '',
  onlineEffect3Desc: '',
  onlineEffect4: '',
  onlineEffect4Desc: '',
});
const { createMessage } = useMessage();
const labelCol = ref<any>({ xs: { span: 24 }, sm: { span: 5 } });
const wrapperCol = ref<any>({ xs: { span: 24 }, sm: { span: 16 } });
const confirmLoading = ref<boolean>(false);
//表单验证
const validatorRules = reactive({
  title: [{ required: true, message: '请输入标题!'},],
  caseType: [{ required: true, message: '请输入案例类型!'},],
  introduce: [{ required: true, message: '请输入介绍描述!'},],
  pic: [{ required: true, message: '请输入项目图片!'},],
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
