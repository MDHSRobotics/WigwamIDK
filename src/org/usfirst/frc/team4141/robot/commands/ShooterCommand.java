package org.usfirst.frc.team4141.robot.commands;

import org.usfirst.frc.team4141.MDRobotBase.MDCommand;
import org.usfirst.frc.team4141.MDRobotBase.MDRobotBase;
import org.usfirst.frc.team4141.MDRobotBase.eventmanager.LogNotification.Level;
import org.usfirst.frc.team4141.robot.subsystems.ShooterSubsystem;


public class ShooterCommand extends MDCommand {
	
	public ShooterCommand(MDRobotBase robot, String name) {
		super(robot, name);
		if(!getRobot().getSubsystems().containsKey("shooterSubsystem")){
			log(Level.ERROR, "initialize()",  "Shooter Subsystem not found");
			throw new IllegalArgumentException("Shooter Subsystem not found");
		}
		shooterSubsystem = (ShooterSubsystem)getRobot().getSubsystems().get("shooterSubsystem");
		requires(shooterSubsystem);
	
	}

	private ShooterSubsystem shooterSubsystem;
	
	protected void execute() {
		shooterSubsystem.shoot();
	}
	@Override
	protected void end() {
		shooterSubsystem.stop();
	}
}
