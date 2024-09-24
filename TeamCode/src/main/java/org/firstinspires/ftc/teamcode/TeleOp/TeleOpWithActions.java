package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


import org.firstinspires.ftc.teamcode.GamepadEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.ExampleSystem;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name="TeleOpWithActionsDemo")
public class TeleOpWithActions extends OpMode {
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public GamepadEx driver, operator;
    public MecanumDrive drive;
    public ExampleSystem exampleSystem;

    @Override
    public void init() {
        driver=new GamepadEx(gamepad1);
        operator=new GamepadEx(gamepad2);
        drive=new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        exampleSystem = new ExampleSystem(hardwareMap);

        runningActions.add(drive.driveAction(driver));
    }

    @Override
    public void loop() {
        TelemetryPacket packet = new TelemetryPacket();
        driver.update();
        operator.update();

        //add actions as needed here, eg:
        if(driver.getButton(GamepadEx.Button.A).justPressed){
            runningActions.add(exampleSystem.setMotor(1));
        }
        if(driver.getButton(GamepadEx.Button.B).justPressed){
            runningActions.add(exampleSystem.setMotor(0));
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

            dash.sendTelemetryPacket(packet);
        }
    }
}
