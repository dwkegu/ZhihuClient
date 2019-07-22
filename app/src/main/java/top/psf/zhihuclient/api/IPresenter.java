package top.psf.zhihuclient.api;

public abstract class IPresenter<T extends BaseView> {
    protected T view;
    public void bindView(T view){
        this.view = view;
    }

    public void unbindView(){
        this.view = null;
    }

}
