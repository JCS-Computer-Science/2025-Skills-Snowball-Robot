package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.TankDrive;

import org.firstinspires.ftc.teamcode.systems.BlockIntake;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;
import org.firstinspires.ftc.teamcode.systems.SlidingArm;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TankDrive Robot")
public class TankDriveRobot extends OpMode {
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public GamepadEx driver;
    public TankDrive drive;
    public ExampleSystem exampleSystem;
    public Elevator elevator;
    public BlockIntake intake;
    public SlidingArm slide;
    private int armInPos = 0;
    private int armOutPos = 0;
    private int armHoldPos = 0;


    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        drive=new TankDrive(hardwareMap);
        intake=new BlockIntake(hardwareMap);
        slide=new SlidingArm(hardwareMap);
        runningActions.add(drive.driveAction(driver));
        runningActions.add(intake.runServos());
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();
        //add actions as needed here, eg:
        if(driver.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(drive.toggleSlowMode());
        }
        if(driver.getButton(GamepadEx.Button.B).justPressed){
            runningActions.add(intake.toggleRun());
            telemetry.addData("intake on? ", intake.running);
        }
        if(driver.getButton(GamepadEx.Button.LEFT_BUMPER).justPressed){
            if(slide.getTarPosition() == armOutPos){
                slide.setPosition(armInPos);
            }else{
                slide.setPosition(armOutPos);
            }
        }
        if(driver.getButton(GamepadEx.Button.RIGHT_BUMPER).justPressed){
            if(slide.getTarPosition() == armOutPos){
                slide.setPosition(armHoldPos);
            }else{
                slide.setPosition(armOutPos);
            }
        }

        updateActions(packet);
    }

    private void updateActions(TelemetryPacket packet){
        List<Action> newActions = new ArrayList<>();
        for(Action action : runningActions){
            action.preview(packet.fieldOverlay());
            if(action.run(packet)){
                newActions.add(action);
            }
            runningActions = newActions;

        }
    }
}
