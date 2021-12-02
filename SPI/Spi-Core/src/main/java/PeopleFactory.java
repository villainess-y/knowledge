
import com.wzy.com.People;

import java.util.Iterator;
import java.util.ServiceLoader;

public class PeopleFactory {
    public void invoker(){
        ServiceLoader<People> services = ServiceLoader.load(People.class);
        Iterator<People> peopleIterator = services.iterator();
        boolean notFound = true;
        while (peopleIterator.hasNext()){
            notFound = false;
            People people = peopleIterator.next();
            people.people();
        }
        if(notFound){
            throw new RuntimeException("未发现具体的实现!");
        }
    }
}
