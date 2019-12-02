package com.hui.commoms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/11/20
 */
public class ResultData<T> {

    private int code;

    private String msg;

    private T data;

    private Date date;

    /**
     * 返回的json格式
     * {
     *     "code": 200,
     *     "msg": "处理成功！",
     *     "data": "xxxxxx",
     *     "date": "2019-12-01T05:24:37.291+0000",
     *     "extend": {
     *         "success": "xxxxx"
     *     }
     * }
     */

    //用户要返回给浏览器的数据
    private Map<String, Object> extend = new HashMap<String, Object>();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultData success(){
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setDate(new Date());
        resultData.setMsg("处理成功！");
        return resultData;
    }

    public static <T> ResultData success(T t){
        ResultData resultData = new ResultData();
        resultData.setCode(200);
        resultData.setDate(new Date());
        resultData.setMsg("处理成功！");
        resultData.setData(t);
        return resultData;
    }

    public static ResultData fail(){
        ResultData resultData = new ResultData();
        resultData.setCode(100);
        resultData.setDate(new Date());
        resultData.setMsg("处理失败！");
        return resultData;
    }

    public static <T> ResultData fail(T t){
        ResultData resultData = new ResultData();
        resultData.setCode(100);
        resultData.setDate(new Date());
        resultData.setMsg("处理失败！");
        resultData.setData(t);
        return resultData;
    }

    public ResultData add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }
    public static void main(String[] args) {
        System.out.println(new Date());
    }

}
