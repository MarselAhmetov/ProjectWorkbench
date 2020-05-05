package team404.project.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CustomScope implements Scope {
    
    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        Object o = objectFactory.getObject();
        System.out.println("Created object: " + o.toString());
        return o;
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
