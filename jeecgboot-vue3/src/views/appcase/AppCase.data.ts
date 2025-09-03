import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
import { getWeekMonthQuarterYear } from '/@/utils';
//列表数据
export const columns: BasicColumn[] = [
  {
    title: '案例类型',
    align: "center",
    dataIndex: 'caseType_dictText'
  },
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
  caseType: {title: '案例类型',order: 0,view: 'list', type: 'string',dictCode: 'caseType',},
  title: {title: '标题',order: 1,view: 'text', type: 'string',},
  introduce: {title: '简介',order: 2,view: 'text', type: 'string',},
  pic: {title: '图片[数组]',order: 3,view: 'image', type: 'string',},
  content: {title: '案例内容',order: 4,view: 'umeditor', type: 'string',},
};
