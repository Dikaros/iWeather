package com.guohui.weather.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dikaros on 2016/5/25.
 * 建议
 */
public class Suggestion {

    /*
                        "comf": {
                            "brf": "较舒适",
                            "txt": "白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"
                        },
                        "cw": {
                            "brf": "较不宜",
                            "txt": "较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"
                        },
                        "drsg": {
                            "brf": "炎热",
                            "txt": "天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"
                        },
                        "flu": {
                            "brf": "少发",
                            "txt": "各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"
                        },
                        "sport": {
                            "brf": "较适宜",
                            "txt": "天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"
                        },
                        "trav": {
                            "brf": "适宜",
                            "txt": "天气较好，是个好天气哦。稍热但是风大，能缓解炎热的感觉，适宜旅游，可不要错过机会呦！"
                        },
                        "uv": {
                            "brf": "强",
                            "txt": "紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。"
                        }
                    }
     */

    public Suggestion(String jsonFile) throws JSONException {
        JSONObject root = new JSONObject(jsonFile);
        comf = new Comf(root.getJSONObject("comf").toString());
        cw = new Cw(root.getJSONObject("cw").toString());
        drsg = new Drsg(root.getJSONObject("drsg").toString());
        flu = new Flu(root.getJSONObject("flu").toString());
        sport = new Sport(root.getJSONObject("sport").toString());
        trav = new Trav(root.getJSONObject("trav").toString());
        uv = new Uv(root.getJSONObject("uv").toString());

    }

    //舒适指数
    Comf comf;

    //洗车指数
    Cw cw;

    //穿衣指数
    Drsg drsg;

    //感冒指数
    Flu flu;

    //运动指数
    Sport sport;

    //旅游指数
    Trav trav;

    //紫外线指数
    Uv uv;



    public class  Comf{

        public Comf(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }

        //舒适度
        String brf;
        //说明
        String txt;

        public String getBrf() {
            return brf;
        }

        public String getTxt() {
            return txt;
        }
    }

    class  Cw{
        public Cw(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }

    class  Drsg{
        public Drsg(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }

    class Flu{
        public Flu(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }

    class Sport{
        public Sport(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }

    class Trav{
        public Trav(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }

    class  Uv{
        public Uv(String jsonFile) throws JSONException {
            JSONObject root = new JSONObject(jsonFile);
            brf = root.getString("brf");
            txt = root.getString("txt");
        }
        //舒适度
        String brf;
        //说明
        String txt;
    }


    public Comf getComf() {
        return comf;
    }

    public void setComf(Comf comf) {
        this.comf = comf;
    }

    public Cw getCw() {
        return cw;
    }

    public void setCw(Cw cw) {
        this.cw = cw;
    }

    public Drsg getDrsg() {
        return drsg;
    }

    public void setDrsg(Drsg drsg) {
        this.drsg = drsg;
    }

    public Flu getFlu() {
        return flu;
    }

    public void setFlu(Flu flu) {
        this.flu = flu;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Trav getTrav() {
        return trav;
    }

    public void setTrav(Trav trav) {
        this.trav = trav;
    }

    public Uv getUv() {
        return uv;
    }

    public void setUv(Uv uv) {
        this.uv = uv;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "comf=" + comf +
                ", cw=" + cw +
                ", drsg=" + drsg +
                ", flu=" + flu +
                ", sport=" + sport +
                ", trav=" + trav +
                ", uv=" + uv +
                '}';
    }
}
