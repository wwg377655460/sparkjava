package util;

import entity.SetMes;

import java.util.*;

/**
 * Created by wsdevotion on 15/10/15.
 */
public class MathUtil {


    /***
     *
     * @param map_p 所有的数据
     * @param map_a 个人上传的数据
     * @param map_f 设置的数据
     * @return 可能的类型
     */
    public static int getMaxType(List<SetMes> map_p, List<SetMes> map_a, List<SetMes> map_f) {

        Map<Integer, Double> map = new HashMap<Integer, Double>();
//        Integer[] sort = new Integer[Data.Type_num];
        Double d;
        List<Double> list_p = MathUtil.turnMapDou(map_p);
        List<Double> list_a = MathUtil.turnMapDou(map_a);
        List<Double> list_f = MathUtil.turnMapDou(map_f);
        int maxnum = MathUtil.getMaxInThree(list_p.size(), list_a.size(), list_f.size());

        for (int i = 1; i < maxnum; i++) {
            if(list_a.size()>i && list_f.size()>i) {//个数都满足
                d = list_p.get(i) * 0.4 + list_a.get(i) * 0.4 + list_f.get(i) * 0.2;
            }else if(list_a.size()<=i) {
                d = list_p.get(i) * 0.6 + list_f.get(i) * 0.4;
            }else if(list_f.size()<=i){
                d = list_p.get(i) * 0.5 + list_a.get(i) * 0.5;
            }else{
                d = list_p.get(i) * 1;
            }
            map.put(i, d);
        }

        List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int max = list.get(0).getKey();
        return max;
    }



    //将个数变成占比
    public static List<Double> turnMapDou(List<SetMes> list){
        int all = 0;
        int type_m = 0;
        List<Double> time_double = new LinkedList<>();
        for(SetMes setMes : list){
            if(setMes.getType_id()>type_m){
                type_m = setMes.getType_id();
            }
            all += setMes.getChoose();
        }
        if(all == 0){
            return time_double;
        }
        for(int i=0; i<=type_m; i++){
            time_double.add(i, 0.0);
        }
        for(SetMes setMes : list){
            time_double.set(setMes.getType_id(), (double)setMes.getChoose() / all);
        }

        return time_double;
    }

    //获取三个数字的最大值
    public static int getMaxInThree(int i, int j, int k){
        return i>j ? (i>k ? i:k) : (j>k ? j:k);
    }
}
