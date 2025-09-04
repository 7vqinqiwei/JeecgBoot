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
    title: '案例类型',
    align: "center",
    dataIndex: 'caseType_dictText'
  },
  {
    title: '项目单位',
    align: "center",
    dataIndex: 'workUnit'
  },
  {
    title: 'Logo',
    align: "center",
    dataIndex: 'logo',
    customRender: render.renderImage,
  },
  {
    title: '介绍描述',
    align: "center",
    dataIndex: 'introduce'
  },
  {
    title: '案例内容',
    align: "center",
    dataIndex: 'content',
  },
  {
    title: '项目图片',
    align: "center",
    dataIndex: 'pic',
    customRender: render.renderImage,
  },
];

// 高级查询数据
export const superQuerySchema = {
  title: {title: '标题',order: 0,view: 'text', type: 'string',},
  caseType: {title: '案例类型',order: 1,view: 'list', type: 'string',dictCode: 'caseType',},
  workUnit: {title: '项目单位',order: 2,view: 'text', type: 'string',},
  logo: {title: 'Logo',order: 3,view: 'image', type: 'string',},
  introduce: {title: '介绍描述',order: 4,view: 'text', type: 'string',},
  content: {title: '案例内容',order: 5,view: 'umeditor', type: 'string',},
  pic: {title: '项目图片',order: 10,view: 'image', type: 'string',},
};
