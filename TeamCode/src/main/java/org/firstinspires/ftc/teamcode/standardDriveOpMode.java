package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@TeleOp(name="standardDrive", group="Linear Opmode")

public class standardDriveOpMode extends LinearOpMode{

    standardHWMap hardware = new standardHWMap();

    @Override

    public void runOpMode() throws InterruptedException {
        float left;
        float right;

        double multiplier = .7;

        hardware.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            left = gamepad1.left_stick_y;
            right = -(gamepad1.right_stick_y);


            //drive
            hardware.leftDrive.setPower(multiplier*left);
            hardware.rightDrive.setPower(multiplier*right);

            //EMBRACE THE JANK

            //winch
            if (gamepad2.right_bumper) {
                hardware.armWinch.setPower(1);
            } else if (gamepad2.left_bumper) {
                hardware.armWinch.setPower(-1);
            } else {
                hardware.armWinch.setPower(0);
            }

            //claws
            if (gamepad2.right_trigger > .05){
                hardware.clawServo1.setPosition(90);
                hardware.clawServo2.setPosition(90);
                hardware.clawServo3.setPosition(0);
                hardware.clawServo4.setPosition(0);

            }
            if (gamepad2.left_trigger > .05){ //inverse of above
                hardware.clawServo1.setPosition(0);
                hardware.clawServo2.setPosition(0);
                hardware.clawServo3.setPosition(90);
                hardware.clawServo4.setPosition(90);

            }

            //fast and slow mode for drive
            if (gamepad1.a){
                if (multiplier == 1.0){
                    multiplier = .7;
                }else if (multiplier == .7){
                    multiplier = .5;
                }else if (multiplier == .5){
                    multiplier = .3;
                }
            }
            if (gamepad1.b){ //inverse of above
                if (multiplier == .7){
                    multiplier = 1.0;
                }else if (multiplier == .5){
                    multiplier = .7;
                }else if (multiplier == .3){
                    multiplier = .5;
                }
            }
            //WILL DESTROY SERVOS IF ABUSED
            //if (gamepad2.right_trigger >= 0.1) {
            //    hardware.clawServo1.setPosition(clawGrip1++);
            //    hardware.clawServo2.setPosition(clawGrip2++);
            //    hardware.clawServo3.setPosition(-(clawGrip3++));
            //    hardware.clawServo4.setPosition(-(clawGrip4++));
            //} else if (gamepad2.left_trigger >= 0.1) {
            //    hardware.clawServo1.setPosition(-(clawGrip1++));
            //    hardware.clawServo2.setPosition(-(clawGrip2++));
            //    hardware.clawServo3.setPosition(clawGrip3++);
            //    hardware.clawServo4.setPosition(clawGrip4++);
            //}

        }
    }
}