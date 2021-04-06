package com.zuo.aidlservice;

/**
 * @author zuo
 * @date 2020/5/15 18:37
 */
public class DataTempSaveUtils {
    private DataTempSaveUtils() {
    }

    public static DataTempSaveUtils getInstance() {
        return SingletonHolder.mInstance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final DataTempSaveUtils mInstance = new DataTempSaveUtils();
    }


    private DataBean dataBean;

    public DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }
}
