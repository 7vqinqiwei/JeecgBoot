import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '标题',
    align: "center",
    dataIndex: 'title'
  },
  {
    title: '图片[数组]',
    align: "center",
    dataIndex: 'pic',
    customRender: render.renderImage,
  },
  {
    title: '案例内容',
    align: "center",
    dataIndex: 'content',
  },
];

// 高级查询数据
export const superQuerySchema = {
  title: {title: '标题',order: 0,view: 'text', type: 'string',},
  pic: {title: '图片[数组]',order: 1,view: 'image', type: 'string',},
  content: {title: '案例内容',order: 2,view: 'umeditor', type: 'string',},
};
