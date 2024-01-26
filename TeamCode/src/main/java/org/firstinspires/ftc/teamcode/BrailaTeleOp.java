package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Position;


@TeleOp

public class BrailaTeleOp extends LinearOpMode {


    private double puterebrat = 1;
    private double position = 0;
    private double opritor = 1;


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor Brat = null;
    private DcMotor Lansator = null;
    private Servo Propulsor = null;
    private DcMotor Intake = null;
    private Servo Rotator = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        Brat = hardwareMap.get(DcMotor.class,"Brat");
        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lansator = hardwareMap.get(DcMotor.class,"Lansator");
        Lansator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lansator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Intake = hardwareMap.get(DcMotor.class,"Intake");
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rotator = hardwareMap.get(Servo.class,"Rotator");
        Propulsor = hardwareMap.get(Servo.class,"Propulsor");



        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial = gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = -gamepad1.left_stick_x;
            double yaw = -gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }




            if (gamepad2.dpad_up) {
                position += 0.01;
            } else if (gamepad2.dpad_down) {
                position -= 0.01;
            }
            position = Range.clip(position, -1, 1);

            if (gamepad2.dpad_right) {
                Brat.setPower(1 * puterebrat);
            } else if (gamepad2.dpad_left) {
                Brat.setPower(-1 * puterebrat);
            } else {
                Brat.setPower(0);
            }
            if (gamepad1.right_trigger > 0) {
                opritor = 0.3;
            } else {
                opritor = 1;
            }
            if(gamepad1.left_trigger > 0)
            {
                opritor = 0.6;
            }



            leftFrontDrive.setPower(leftFrontPower * opritor);
            rightFrontDrive.setPower(rightFrontPower * opritor);
            leftBackDrive.setPower(leftBackPower * opritor);
            rightBackDrive.setPower(rightBackPower * opritor);

            if (gamepad1.triangle) {
                Lansator.setPower(1);
                sleep(1000);
                Propulsor.setPosition(-1);
            }
            if (gamepad1.circle)
                Lansator.setPower(0);

            if(gamepad2.right_trigger > 0.3)
            {
                Intake.setPower(1);
            } else if(gamepad2.left_trigger > 0.3)
            {
                Intake.setPower(-1);
            }
            else
            {
                Intake.setPower(0);
            }
            if(gamepad2.right_bumper)
            {
                Brat.setTargetPosition(10264);
                Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Brat.setPower(1);
                while(opModeIsActive() && Brat.isBusy() && Math.abs(Brat.getTargetPosition() - Brat.getCurrentPosition()) > 30){
                    telemetry.addData("Pozitie Brat", Brat.getCurrentPosition());
                    telemetry.update();
                }
                Brat.setPower(0);
                Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            if(gamepad2.left_bumper)
            {
                Brat.setTargetPosition(6511 );
                Brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Brat.setPower(1);
                while(opModeIsActive() && Brat.isBusy() && Math.abs(Brat.getTargetPosition() - Brat.getCurrentPosition()) > 30){
                    telemetry.addData("Pozitie Brat", Brat.getCurrentPosition());
                    telemetry.update();
                }
                Brat.setPower(0);
                Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }
            Rotator.setPosition(position);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Pozitie Servo", Rotator.getPosition());
            telemetry.addData("Pozitie Brat",Brat.getCurrentPosition());
            telemetry.update();

        }

    }

}