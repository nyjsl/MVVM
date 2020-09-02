package org.nyjsl.jetpack.mvvm.base.decorator;

import android.content.Context;

import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.core.UriRequest;

/**
 * @author : weixing
 * @date : 2020/9/2 9:49 AM
 */
public interface IRouter {

    default void startUri(UriRequest request){
        Router.startUri(request);
    }

    default void startUri(Context context, String uri) {
        Router.startUri(context,uri);
    }

}
