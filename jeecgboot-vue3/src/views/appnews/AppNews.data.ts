import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '标题',
    align:"center",
    dataIndex: 'title'
   },
   {
    title: '简介',
    align:"center",
    dataIndex: 'introduce'
   },
   {
    title: '发布时间',
    align:"center",
    dataIndex: 'publishTime'
   },
   {
    title: '发布人',
    align:"center",
    dataIndex: 'publishBy'
   },
   {
    title: '内容',
    align:"center",
    dataIndex: 'content'
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '标题',
    field: 'title',
    component: 'Input',
  },
  {
    label: '简介',
    field: 'introduce',
    component: 'Input',
  },
  {
    label: '发布时间',
    field: 'publishTime',
    component: 'DatePicker',
    componentProps: {
       showTime: true,
       valueFormat: 'YYYY-MM-DD HH:mm:ss'
     },
  },
  {
    label: '发布人',
    field: 'publishBy',
    component: 'Input',
  },
  {
    label: '内容',
    field: 'content',
    component: 'Input',
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];

// 高级查询数据
export const superQuerySchema = {
  title: {title: '标题',order: 0,view: 'text', type: 'string',},
  introduce: {title: '简介',order: 1,view: 'text', type: 'string',},
  publishTime: {title: '发布时间',order: 2,view: 'datetime', type: 'string',},
  publishBy: {title: '发布人',order: 3,view: 'text', type: 'string',},
  content: {title: '内容',order: 4,view: 'text', type: 'string',},
};

/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}