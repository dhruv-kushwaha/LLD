package Lesson10_Command_Design_Pattern;

interface ICommand {
    void execute();

    void undo();
}

class Light {
    public void on() {
        System.out.println("Turned the light ON");
    }

    public void off() {
        System.out.println("Turned the light OFF");
    }
}

class Fan {
    public void on() {
        System.out.println("Turned the fan ON");
    }

    public void off() {
        System.out.println("Turned the fan OFF");
    }
}

class LightCommand implements ICommand {
    private Light light;

    public LightCommand(Light l) {
        this.light = l;
    }

    @Override
    public void execute() {
        this.light.on();
    }

    @Override
    public void undo() {
        this.light.off();
    }
}

class FanCommand implements ICommand {
    private Fan fan;

    public FanCommand(Fan f) {
        this.fan = f;
    }

    @Override
    public void execute() {
        this.fan.on();
    }

    @Override
    public void undo() {
        this.fan.off();
    }
}

class RemoteController {
    private static final int numOfButtons = 4;
    private ICommand[] buttons;
    private boolean[] buttonsPressed;

    public RemoteController() {
        buttons = new ICommand[numOfButtons];
        buttonsPressed = new boolean[numOfButtons];
        for (int i = 0; i < numOfButtons; i++) {
            buttons[i] = null;
            buttonsPressed[i] = false;
        }
    }

    public void setButton(int idx, ICommand command) {
        if (idx < numOfButtons && idx >= 0) {
            buttons[idx] = null;
            buttonsPressed[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < numOfButtons && buttons[idx] != null) {
            if (!buttonsPressed[idx]) {
                buttons[idx].execute();
            } else {
                buttons[idx].undo();
            }
            buttonsPressed[idx] = !buttonsPressed[idx];
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    }
}

public class CommandDesignPattern {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        RemoteController remote = new RemoteController();

        remote.setButton(0, new LightCommand(livingRoomLight));
        remote.setButton(1, new FanCommand(ceilingFan));

        // Simulate button presses (toggle behavior)
        System.out.println("--- Toggling Light Button 0 ---");
        remote.pressButton(0); // ON
        remote.pressButton(0); // OFF

        System.out.println("--- Toggling Fan Button 1 ---");
        remote.pressButton(1); // ON
        remote.pressButton(1); // OFF

        // Press unassigned button to show default message
        System.out.println("--- Pressing Unassigned Button 2 ---");
        remote.pressButton(2);
    }
}
