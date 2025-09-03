import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '手机号',
    align: "center",
    dataIndex: 'phone'
  },
  {
    title: '创建日期',
    align: "center",
    dataIndex: 'createTime'
  },
];

// 高级查询数据
export const superQuerySchema = {
  phone: {title: '手机号',order: 1,view: 'text', type: 'string',},
  createTime: {title: '创建日期',order: 2,view: 'datetime', type: 'string',},
};
