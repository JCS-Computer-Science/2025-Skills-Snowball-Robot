package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;
import java.util.Objects;

public class GamepadEx {
    public Gamepad gamepad;
    public HashMap<String,GamepadButton> buttons;

    public enum Button {
        A, B, X, Y, LEFT_BUMPER, RIGHT_BUMPER, LEFT_STICK, RIGHT_STICK, DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT
    }

    public GamepadEx(Gamepad g){
        this.gamepad=g;
        this.buttons= new HashMap<>();
        for(Button b: Button.values()){
            buttons.put(b.toString(),new GamepadButton());
        }
    }

    public void update(){
        for(Button b: Button.values()){
            Objects.requireNonNull(buttons.get(b.toString())).update(getButtonState(b));
        }
    }

    public GamepadButton getButton(Button b){
        return buttons.get(b.toString());
    }

    private boolean getButtonState(Button b){
        switch (b){
            case A:return gamepad.a;
            case B:return gamepad.b;
            case X:return gamepad.x;
            case Y:return gamepad.y;
            case DPAD_UP:return gamepad.dpad_up;
            case DPAD_DOWN:return gamepad.dpad_down;
            case DPAD_LEFT:return gamepad.dpad_left;
            case DPAD_RIGHT:return gamepad.dpad_right;
            case LEFT_STICK:return gamepad.left_stick_button;
            case LEFT_BUMPER:return gamepad.left_bumper;
            case RIGHT_STICK:return gamepad.right_stick_button;
            case RIGHT_BUMPER:return gamepad.right_bumper;
            default: return false;
        }
    }
}
