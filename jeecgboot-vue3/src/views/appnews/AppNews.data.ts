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
    title: '简介',
    align: "center",
    dataIndex: 'introduce'
  },
  {
    title: '图片数组[,]',
    align: "center",
    dataIndex: 'pic',
    customRender: render.renderImage,
  },
  {
    title: '发布时间',
    align: "center",
    dataIndex: 'publishTime'
  },
  {
    title: '发布人',
    align: "center",
    dataIndex: 'publishBy_dictText'
  },
  {
    title: '内容',
    align: "center",
    dataIndex: 'content',
  },
];

// 高级查询数据
export const superQuerySchema = {
  title: {title: '标题',order: 0,view: 'text', type: 'string',},
  introduce: {title: '简介',order: 1,view: 'textarea', type: 'string',},
  pic: {title: '图片数组[,]',order: 2,view: 'image', type: 'string',},
  publishTime: {title: '发布时间',order: 3,view: 'datetime', type: 'string',},
  publishBy: {title: '发布人',order: 4,view: 'sel_user', type: 'string',},
  content: {title: '内容',order: 5,view: 'umeditor', type: 'string',},
};
