package juc.UtilsTest;

import Utils.SleepHelper;

import java.util.concurrent.Phaser;

/**
 *
 */
public class PhaserTest {

    static MarriagePhaser phaser = new MarriagePhaser();


    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了\t" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了\t" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了\t" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("新郎新娘嘿嘿嘿\t" + registeredParties);
                    System.out.println();
                    return true;
                default:return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name){
            this.name = name;
        }

        private void arrival(){
            SleepHelper.sleepMilli(500);
            System.out.println(name + "到达现场");
            phaser.arriveAndAwaitAdvance();
        }

        private void eat(){
            SleepHelper.sleepMilli(500);
            System.out.println(name + "吃完");
            phaser.arriveAndAwaitAdvance();
        }

        private void leave(){
            SleepHelper.sleepMilli(500);
            System.out.println(name + "走了");
            phaser.arriveAndAwaitAdvance();
        }

        private void hug(){
            if("新郎".equals(name) || "新娘".equals(name)){
                SleepHelper.sleepSeconds(1);
                System.out.println(name + "洞房");
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }


        @Override
        public void run() {
            arrival();

            eat();

            leave();

            hug();
        }


    }
}
