// IMyAIDLService.aidl
package com.zuo.aidlservice;
parcelable SocketParseBean;

interface IMyAIDLService {
   //获取展示的数据
   SocketParseBean getData();
}
