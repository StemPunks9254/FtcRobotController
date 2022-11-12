package org.firstinspires.ftc.robotcontroller;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "main")
public class TeleOpMain extends OpMode {
    //wheels of the robot
    protected DcMotor frontLeft;
    protected DcMotor frontRight;
    protected DcMotor backLeft;
    protected DcMotor backRight;
    //motor responsible for moving the extrusion lift up and down
    protected DcMotor extrusionLiftVertical;
    //claws (not yet designed)
    protected Servo clawLeft;
    protected Servo clawRight;
    protected Servo clawRotation;
    protected double turnSpeed = 0.5;

    @Override
    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        extrusionLiftVertical = hardwareMap.get(DcMotor.class, "extrusionLiftVertical");
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawRotation = hardwareMap.get(Servo.class, "clawRotation");

    }

    @Override
    public void loop() {
        //turning code
        double right_speed = -gamepad1.left_stick_y;
        double left_speed = -gamepad1.left_stick_y;

        turnSpeed = gamepad1.right_stick_x;

        right_speed -= turnSpeed;
        left_speed += turnSpeed;

        right_speed = clamp(right_speed, -1.0, 1.0);
        left_speed = clamp(left_speed, -1.0, 1.0);

        // normal movement code
        frontLeft.setPower(left_speed);
        frontRight.setPower(right_speed);
        backLeft.setPower(left_speed);
        backRight.setPower(right_speed);

        // prelim code for extrusion lift, pressing "y" moves it up, pressing "a" moves it down
        if(gamepad2.y) {
            extrusionLiftVertical.setPower(0.5);
        } else if (gamepad2.a) {
            extrusionLiftVertical.setPower(-0.5);
        }
        extrusionLiftVertical.setPower(0);
    }

    private static double clamp(double num, double min, double max) {
        return Math.max(min, Math.min(max, num));
    }
}
