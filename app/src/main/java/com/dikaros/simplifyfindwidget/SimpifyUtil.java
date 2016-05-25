package com.dikaros.simplifyfindwidget;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.dikaros.simplifyfindwidget.annotation.FindView;
import com.dikaros.simplifyfindwidget.annotation.OnClick;
import com.dikaros.simplifyfindwidget.annotation.OnItemClick;
import com.dikaros.simplifyfindwidget.annotation.OnItemLongClick;
import com.dikaros.simplifyfindwidget.annotation.OnLongClick;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Dikaros on 2016/5/18.
 */
public class SimpifyUtil {


    public static void findAllViews(Object o,View view){
        try {


            //获取所有的成员变量
            Field[] fields = o.getClass().getDeclaredFields();
            //遍历
            for (Field field : fields) {
                //如果属性是view并且被FindView这个注解修饰的并且是view的子类
                if (field.isAnnotationPresent(FindView.class)) {
                    //成员变量类型
                    Class fieldType = field.getType();

                    //获取注解的value
                    FindView fv = field.getAnnotation(FindView.class);

                    //通过findViewById方法获取的内容
                    Object newView;
                    //如果是-1（默认值）
                    if (fv.value() == -1) {
                        throw new Exception("请指定id");

                    } else {
                        newView = fieldType.cast(view.findViewById(fv.value()));
                    }

                    if (newView == null) {
                        throw new Exception("属性名" + field.getName() + "与xml中的id不一致");
                    }
                    //设置属性可以访问
                    field.setAccessible(true);

                    //设置找到的view
                    field.set(o, newView);
                    //关闭属性可以访问
                    field.setAccessible(false);

                }
            }
        } catch (Exception e) {
            Log.e("simpifyUtil", e.getMessage());
        }
    }



    /**
     * 查找到所有的view
     * 注意View
     */
    public static void findAllViews(Activity activity) {

        try {


            Class r = Class.forName(activity.getPackageName() + ".R$id");
            Field[] ids = r.getDeclaredFields();
            HashMap<String, Integer> idMap = new HashMap<>();
            for (Field f :
                    ids) {
                //获取数据
                if (f.getType() == int.class) {
                    idMap.put(f.getName(), (int) f.get(r));
                }
            }


            //获取所有的成员变量
            Field[] fields = activity.getClass().getDeclaredFields();
            //遍历
            for (Field field : fields) {
                //如果属性是view并且被FindView这个注解修饰的并且是view的子类
                if (field.isAnnotationPresent(FindView.class)) {
                    //成员变量类型
                    Class fieldType = field.getType();

                    //获取注解的value
                    FindView fv = field.getAnnotation(FindView.class);

                    //通过findViewById方法获取的内容
                    Object newView;
                    //如果是-1（默认值）
                    if (fv.value() == -1) {
                        newView = fieldType.cast(activity.findViewById(idMap.get(field.getName())));
                    } else {
                        newView = fieldType.cast(activity.findViewById(fv.value()));
                    }

                    if (newView == null) {
                        throw new Exception("属性名" + field.getName() + "与xml中的id不一致");
                    }
                    //设置属性可以访问
                    field.setAccessible(true);

                    //设置找到的view
                    field.set(activity, newView);
                    //关闭属性可以访问
                    field.setAccessible(false);

                }
            }
        } catch (Exception e) {
            Log.e("simpifyUtil", e.getMessage());
        }
    }


    /**
     * 为所有的view添加监听器
     *
     * @param activity
     */
    public static void registListenerforAll(final Activity activity) {
        try {


            //获取所有的成员变量
            Field[] fields = activity.getClass().getDeclaredFields();
            //遍历
            for (Field field : fields) {

                /*
                快速注册onClick事件
                 */
                if (field.isAnnotationPresent(OnClick.class) && View.class.isAssignableFrom(field.getType())) {
                    //成员变量类型
                    Class fieldType = field.getType();
                    //获取注解对象
                    OnClick click = field.getAnnotation(OnClick.class);
                    //获取注解对象的value
                    String methodName = click.value();

                    //需要调用的方法所属的类
                    Class from = click.from();
                    //如果from是默认的则调用当前类的方法
                    if (from == Void.class) {
                        from = activity.getClass();
                    }

                    //点击方法
                    final Method clickMethod = from.getMethod(methodName, View.class);

                    //获取setOnClicklistener方法
                    Method m = fieldType.getMethod("setOnClickListener", View.OnClickListener.class);
                    //设置field可设置
                    field.setAccessible(true);

                    m.invoke(field.get(activity), new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            try {
                                //调用指定方法名的方法
                                clickMethod.invoke(activity, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //取消field可设置
                    field.setAccessible(false);
                }



                /*
                快速注册OnItemClick事件
                 */
                if (field.isAnnotationPresent(OnItemClick.class) && AdapterView.class.isAssignableFrom(field.getType())) {
                    //成员变量类型
                    Class fieldType = field.getType();
                    //获取注解对象
                    OnItemClick click = field.getAnnotation(OnItemClick.class);
                    //获取注解对象的value
                    String methodName = click.value();
                    //需要调用的方法所属的类
                    Class from = click.from();
                    //如果from是默认的则调用当前类的方法
                    if (from == Void.class) {
                        from = activity.getClass();
                    }

                    Log.i("from",activity.getClass().getName());

                    //点击方法
                    final Method clickMethod = from.getDeclaredMethod(methodName, AdapterView.class, View.class, int.class, long.class);

                    //获取setOnClicklistener方法
                    Method m = fieldType.getMethod("setOnItemClickListener", AdapterView.OnItemClickListener.class);
                    //设置field可设置
                    field.setAccessible(true);

                    m.invoke(field.get(activity), new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                clickMethod.invoke(activity, parent, view, position, id);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    //取消field可设置
                    field.setAccessible(false);
                }

                /*
                快速注册onLongClick事件
                 */
                if (field.isAnnotationPresent(OnLongClick.class) && View.class.isAssignableFrom(field.getType())) {
                    //成员变量类型
                    Class fieldType = field.getType();
                    //获取注解对象
                    OnLongClick click = field.getAnnotation(OnLongClick.class);
                    //获取注解对象的value
                    String methodName = click.value();
                    Class from = click.from();
                    //如果from是默认的则调用当前类的方法
                    if (from == Void.class) {
                        from = activity.getClass();
                    }
                    //点击方法
                    final Method clickMethod = from.getMethod(methodName, View.class);

                    //获取setOnClicklistener方法
                    Method m = fieldType.getMethod("setOnLongClickListener", View.OnLongClickListener.class);
                    //设置field可设置
                    field.setAccessible(true);

                    m.invoke(field.get(activity), new View.OnLongClickListener() {

                        @Override
                        public boolean onLongClick(View v) {

                            try {
                                return (boolean) clickMethod.invoke(activity, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });
                    //取消field可设置
                    field.setAccessible(false);
                }


                /*
                快速注册OnItemOnClick使劲
                 */
                if (field.isAnnotationPresent(OnItemLongClick.class) && AdapterView.class.isAssignableFrom(field.getType())) {
                    //成员变量类型
                    Class fieldType = field.getType();
                    //获取注解对象
                    OnItemLongClick click = field.getAnnotation(OnItemLongClick.class);
                    //获取注解对象的value
                    String methodName = click.value();
                    Class from = click.from();
                    //如果from是默认的则调用当前类的方法
                    if (from == Void.class) {
                        from = activity.getClass();
                    }
                    //点击方法
                    final Method clickMethod = from.getMethod(methodName, AdapterView.class, View.class, int.class, long.class);

                    //获取setOnClicklistener方法
                    Method m = fieldType.getMethod("setOnItemLongClickListener", AdapterView.OnItemLongClickListener.class);
                    //设置field可设置
                    field.setAccessible(true);

                    m.invoke(field.get(activity), new AdapterView.OnItemLongClickListener() {

                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                return (boolean) clickMethod.invoke(activity, parent, view, position, id);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }

                    });
                    //取消field可设置
                    field.setAccessible(false);
                }

            }
        } catch (Exception e) {
//            Log.e("simpifyUtil", e.getMessage());

            e.printStackTrace();        }
    }
}
