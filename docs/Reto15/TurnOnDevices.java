package docs.Reto15;

// Clase adaptadora
class CoffeMakerAdapter implements Connectable {
    private CoffeMaker coffeMaker;

    public CoffeMakerAdapter(CoffeMaker coffeMaker) {
        this.coffeMaker = coffeMaker;
    }

    @Override
    public void turnOn() {
        coffeMaker.on();
    }

    @Override
    public void turnOff() {
        coffeMaker.off();
    }

    @Override
    public boolean isOn() {
        return !coffeMaker.isOff();
    }
}

// Clase existente CoffeMaker
class CoffeMaker {
    public void on() {
        System.out.println("Cafetera encendida.");
    }

    public void off() {
        System.out.println("Cafetera apagada.");
    }

    public boolean isOff() {
        // Aquí suponemos que la cafetera tiene un estado inicial apagado.
        return false; // Devuelve falso para simular que está encendida.
    }
}

// Modificación de TurnOnDevices
public class TurnOnDevices {
    public static void main(String[] args) {
        turnOnDevice(new Lamp());
        turnOnDevice(new Computer());
        turnOnDevice(new CoffeMakerAdapter(new CoffeMaker())); // Usando la cafetera con adaptador
    }

    private static void turnOnDevice(Connectable device) {
        device.turnOn();
        System.out.println("¿El dispositivo está encendido? " + device.isOn());
    }
}

// Clases existentes
class Lamp implements Connectable {
    @Override
    public void turnOn() {
        System.out.println("Lámpara encendida.");
    }

    @Override
    public void turnOff() {
        System.out.println("Lámpara apagada.");
    }

    @Override
    public boolean isOn() {
        return true;
    }
}

class Computer implements Connectable {
    @Override
    public void turnOn() {
        System.out.println("Computadora encendida.");
    }

    @Override
    public void turnOff() {
        System.out.println("Computadora apagada.");
    }

    @Override
    public boolean isOn() {
        return true;
    }
}

interface Connectable {
    void turnOn();
    void turnOff();
    boolean isOn();
}