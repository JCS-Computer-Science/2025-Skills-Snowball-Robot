package org.firstinspires.ftc.teamcode;

public class GamepadButton {
    private boolean oldState=false;
    private boolean isPressed=false;
    public boolean justPressed=false;
    public boolean isHeld=false;
    public boolean justReleased=false;

    public void update(boolean state){
        this.justPressed=!this.oldState&&state;
        this.isHeld=this.oldState&&state;
        this.justReleased=this.oldState&&!state;

        this.oldState=state;
        this.isPressed=state;
    }
}
