import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '名称',
    align: "center",
    dataIndex: 'name'
  },
  {
    title: '排序字段',
    align: "center",
    dataIndex: 'sort'
  },
  {
    title: 'Banner描述',
    align: "center",
    dataIndex: 'posDesc'
  },
];

// 高级查询数据
export const superQuerySchema = {
  name: {title: '名称',order: 0,view: 'text', type: 'string',},
  sort: {title: '排序字段',order: 1,view: 'number', type: 'number',},
  posDesc: {title: 'Banner描述',order: 2,view: 'text', type: 'string',},
};
