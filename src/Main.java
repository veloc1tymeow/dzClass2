class Participant {
    protected String name;
    protected int maxRunDistance;
    protected int maxJumpHeight;
    protected boolean active; // Добавлен флаг для отслеживания активности участника

    public Participant(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.active = true; // Изначально участник активен
    }

    public void run(int distance) {
        if (active) {
            if (distance <= maxRunDistance) {
                System.out.println(name + " успешно пробежал " + distance + " метров");
            } else {
                System.out.println(name + " не смог пробежать " + distance + " метров и выбывает");
                active = false; // Если не смог пробежать, делаем участника неактивным
            }
        } else {
            System.out.println(name + " уже выбыл");
        }
    }

    public void jump(int height) {
        if (active) {
            if (height <= maxJumpHeight) {
                System.out.println(name + " успешно прыгнул на высоту " + height + " метра");
            } else {
                System.out.println(name + " не смог прыгнуть на высоту " + height + " метра и выбывает");
                active = false; // Если не смог прыгнуть, делаем участника неактивным
            }
        } else {
            System.out.println(name + " уже выбыл");
        }
    }
}

class Human extends Participant {
    public Human(String name, int maxRunDistance, int maxJumpHeight) {
        super(name, maxRunDistance, maxJumpHeight);
    }
}

class Cat extends Participant {
    public Cat(String name, int maxRunDistance, int maxJumpHeight) {
        super(name, maxRunDistance, maxJumpHeight);
    }
}

class Robot extends Participant {
    public Robot(String name, int maxRunDistance, int maxJumpHeight) {
        super(name, maxRunDistance, maxJumpHeight);
    }
}

class Track {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    public void pass(Participant participant) {
        participant.run(length);
    }
}

class Wall {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public void pass(Participant participant) {
        participant.jump(height);
    }
}

public class Main {
    public static void main(String[] args) {
        Participant[] participants = {
                new Human("Иван", 100, 2),
                new Cat("Барсик", 150, 3),
                new Robot("R2D2", 200, 1)
        };

        Object[] obstacles = {
                new Track(120),
                new Wall(2),
                new Track(160),
                new Wall(3),
                new Track(180),
                new Wall(1)
        };

        for (Participant participant : participants) {
            for (Object obstacle : obstacles) {
                if (participant.active) {
                    if (obstacle instanceof Track) {
                        ((Track) obstacle).pass(participant);
                    } else if (obstacle instanceof Wall) {
                        ((Wall) obstacle).pass(participant);
                    }
                } else {
                    break; // Если участник уже неактивен, прерываем прохождение препятствий
                }
            }
            System.out.println();
        }
    }
}
