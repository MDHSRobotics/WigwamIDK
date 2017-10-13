package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.MDRobotBase.MDRobotBase;
import org.usfirst.frc.team4141.MDRobotBase.MDSubsystem;
import org.usfirst.frc.team4141.MDRobotBase.config.ConfigSetting;

import edu.wpi.first.wpilibj.SpeedController;

public class ShooterSubsystem extends MDSubsystem {
	
	private double shootSpeed = 1;
	
	private SpeedController shooterController1;
	private SpeedController shooterController2;
	
	public static String motorName1="shootermotor1";
	public static String motorName2="shootermotor2";


	public MDSubsystem configure(){
		super.configure();

		if(getMotors()==null 
				|| !getMotors().containsKey(motorName1))
			throw new IllegalArgumentException("Invalid motor configuration for shoot system.");
		shooterController1 = (SpeedController)(getMotors().get(motorName1));
		
		if(getMotors()==null 
				|| !getMotors().containsKey(motorName2))
			throw new IllegalArgumentException("Invalid motor configuration for shoot system.");
		shooterController2 = (SpeedController)(getMotors().get(motorName2));
		return this;
	}
	
	public ShooterSubsystem(MDRobotBase robot, String name) {
		super(robot, name);
		// TODO Auto-generated constructor stub
	}
	
	public void shoot(){
		shooterController1.set(shootSpeed);
		shooterController2.set(-shootSpeed);
	}
		
	public void stop(){
		shooterController1.stopMotor();
		shooterController2.stopMotor();
		
	}
	
	@Override
	protected void setUp() {
		
		if(getConfigSettings().containsKey("shootSpeed")) shootSpeed = getConfigSettings().get("shootSpeed").getDouble();
		
	}

	@Override
	public void settingChangeListener(ConfigSetting changedSetting) {
		
		if(changedSetting.getName().equals("shootSpeed")) shootSpeed = changedSetting.getDouble();

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
