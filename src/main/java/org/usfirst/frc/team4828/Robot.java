package org.usfirst.frc.team4828;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team4828.Vision.Vision;

public class Robot extends IterativeRobot {
    private Joystick driveStick;
    private DriveTrain drive;
    private Vision vision = null;
    private Shooter rightShooter, leftShooter;
    private DigitalInput[] dipSwitch;
    private int autonSelect;
    private Climber climb;
    private Hopper hopper;
    private ServoGroup gearGobbler;

    @Override
    public void robotInit() {
        super.robotInit();
        System.out.println("THE ROBOT TURNED ON");
        driveStick = new Joystick(0);
        drive = new DriveTrain(
                Ports.DT_FRONT_LEFT,
                Ports.DT_BACK_LEFT,
                Ports.DT_FRONT_RIGHT,
                Ports.DT_BACK_RIGHT
        );

        climb = new Climber(Ports.CLIMBER_1, Ports.CLIMBER_2);
        hopper = new Hopper(Ports.AGITATOR, Ports.INTAKE);

        // Master is the one on the right if you are looking at the back of the shooter
        rightShooter = new Shooter(Ports.MOTOR_RIGHT, Ports.SERVO_RIGHT_MASTER, Ports.SERVO_RIGHT_SLAVE);
        leftShooter = new Shooter(Ports.MOTOR_LEFT, Ports.SERVO_LEFT_MASTER, Ports.SERVO_LEFT_SLAVE);
        rightShooter.servos.calibrate(1, .3, 0);
        rightShooter.servos.calibrate(2, .6, 1);
        leftShooter.servos.calibrate(1, .75, .35);
        leftShooter.servos.calibrate(2, .3, .75);

        dipSwitch = new DigitalInput[4];
        dipSwitch[0] = new DigitalInput(Ports.DIPSWITCH_1);
        dipSwitch[1] = new DigitalInput(Ports.DIPSWITCH_2);
        dipSwitch[2] = new DigitalInput(Ports.DIPSWITCH_3);
        dipSwitch[3] = new DigitalInput(Ports.DIPSWITCH_4);

        vision = new Vision(Ports.US_CHANNEL);
        vision.start();

        gearGobbler = new ServoGroup(Ports.ACTIVE_GEAR_LEFT, Ports.ACTIVE_GEAR_RIGHT);
        gearGobbler.calibrate(1, 0.45, 0.08);
        gearGobbler.calibrate(2, 0.287, 0.702);

        // Start pointing straight up
        //TODO: check if setting servos in robotInit actually works
        rightShooter.servos.set(0);
        leftShooter.servos.set(0);
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
        // Bit shift the switches repeatedly to read it into an int
        for (int i = 0; i < 4; i++) {
            autonSelect += (dipSwitch[i].get() ? 1 : 0) * (1 << i);
        }
        System.out.println("Entering auton number " + autonSelect);
        drive.reset();
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        double distance = 93.3; //inches
        switch (autonSelect) {
            case 0:
                // Shoot 10 fuel from the base of the boiler
                break;
            case 1:
                // Place gear on right side
                drive.moveDistance(-distance);
                drive.placeGear(3, vision);
                break;
            case 2:
                // Place gear on center
                drive.placeGear(2, vision);
                break;
            case 3:
                // Place gear on left side
                drive.moveDistance(distance);
                drive.placeGear(1, vision);
                break;
            case 4:
                // Shoot 10 fuel and place gear on left side
                break;
            case 5:
                // The crazy running into hopper and shooting tons of balls plan
                break;
            default:
                // Do nothing
        }
        Timer.delay(.1);
    }

    @Override
    public void teleopInit() {
        super.teleopInit();
        System.out.println("Entering teleop...");
    }

    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
        drive.mecanumDrive(driveStick.getX(), driveStick.getY(), driveStick.getTwist()/4);
        if (driveStick.getRawButton(11)) {
            drive.reset();
        }
    }

    @Override
    public void testInit() {
        super.testInit();
        System.out.println("Entering test...");
    }

    @Override
    public void testPeriodic() {
        System.out.println(drive);
        Timer.delay(.1);
    }

    @Override
    public void disabledInit() {
        System.out.println("Disabling robot");
        if (vision != null) {
            vision.terminate();
            System.out.println("Stopping thread");
        }
    }
}
