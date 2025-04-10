package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.TankDrive;

import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;

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


    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        drive=new TankDrive(hardwareMap);
        //exampleSystem = new ExampleSystem(hardwareMap);
        //elevator = new Elevator(hardwareMap);

        runningActions.add(drive.driveAction(driver));
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();

        //telemetry.addData("elevatorPosition", elevator.getPosition());

        //add actions as needed here, eg:
        if(driver.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(drive.toggleSlowMode());
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
