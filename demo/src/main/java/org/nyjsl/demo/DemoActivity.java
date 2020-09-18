package org.nyjsl.demo;



import org.nyjsl.demo.bean.DemoBean;
import org.nyjsl.demo.bean.DiBen;
import org.nyjsl.demo.databinding.DemoActivityDemoBinding;
import org.nyjsl.jetpack.mvvm.base.BaseNoVMActivity;

/**
 * databinding 绑定表达式中可以使用以上关键字和运算符
 *
 *  算术 + - / * %
 *  字符串拼接 +
 *  逻辑
 *  位
 *  一元
 *  移位
 *  关系
 *  instanceof
 *  组()
 *  字面量 ,字符串 ,数字 null
 *  方法调用
 *  字段访问
 *  数组访问[]
 *  三元操作符 ?:
 *
 *
 *  空合并操作符
 *
 *
 *  双向绑定比单向绑定多一个=号
 */
public class DemoActivity extends BaseNoVMActivity<DemoActivityDemoBinding> {

    private DemoBean db = new DemoBean();
    private DiBen dd = new DiBen();

    @Override
    protected void setData() {
        binding.setDd(dd);
        binding.setData(db);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setModel() {
        binding.setModel(this);
    }

    @Override
    protected void initView() {

    }

    public void increase() {
        db.setIndex(db.getIndex()+1);
        db.setAge(db.getAge()+1);
        //单向绑定
        binding.setData(db);
    }

    public void decrease() {
        db.setIndex(db.getIndex()-1);
        db.setAge(db.getAge()-1);
        binding.setData(db);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.demo_activity_demo;
    }
}