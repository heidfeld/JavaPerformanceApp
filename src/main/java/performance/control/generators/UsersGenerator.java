package performance.control.generators;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class UsersGenerator {

    public static List<User> generate(Integer count) {
        List<User> result = new ArrayList<>();
        for(int i = 0 ; i < count ; i++) {
            User user = new User();
            user.setAge(ThreadLocalRandom.current().nextInt(100));
            user.setBirthDate(getRandomDate());
            user.setName(getRandomName());
            user.setSurname(getRandomSurname());
            user.setUuid(UUID.randomUUID().toString());
            result.add(user);
        }
        return result;
    }

    private static Date getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(1900, 2010);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return gc.getTime();
    }

    private static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private static String getRandomName(){
        Random r1 = new Random();
        String[] names = new String[]{
                "Tom","Jacob","Jake",
                "Ethan","Jonathan","Tyler","Samuel","Nicholas","Angel",
                "Jayden","Nathan","Elijah","Christian","Gabriel","Benjamin",
                "Emma","Aiden","Ryan","James","Abigail","Logan","John",
                "Daniel","Alexander","Isabella","Anthony","William","Christopher","Matthew","Emily","Madison",
                "Rob","Ava","Olivia","Andrew","Joseph","David","Sophia","Noah",
                "Justin",};

        int indexF = r1.nextInt(names.length - 1);
        return names[indexF];
    }

    private static String getRandomSurname(){
        Random r1 = new Random();
        String[] lNames = new String[]{
                "Smith","Johnson","Williams","Jones","Brown","Davis","Miller","Wilson","Moore",
                "Taylor","Anderson","Thomas","Jackson","White","Harris","Martin","Thompson","Garcia",
                "Martinez","Robinson","Clark","Lewis","Lee","Walker","Hall","Allen","Young",
                "King","Wright","Hill","Scott","Green","Adams","Baker","Carter","Turner",
        };
        int indexL = r1.nextInt(lNames.length - 1);
        return lNames[indexL];
    }

}
