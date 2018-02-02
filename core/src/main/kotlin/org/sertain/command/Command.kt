@file:Suppress("unused", "RedundantVisibilityModifier")
@file:JvmName("CommandUtils")
package org.sertain.command

import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.Subsystem
import java.util.concurrent.TimeUnit

private typealias WpiLibCommand = edu.wpi.first.wpilibj.command.Command

/** @see CommandGroup.addSequential */
public infix fun Command.then(command: Command) =
        CommandGroup().addSequential(this).addSequential(command)

/** @see CommandGroup.addParallel */
public infix fun Command.and(command: Command) =
        CommandGroup().addParallel(this).addParallel(command)

/** @see CommandGroup.addSequential */
public infix fun CommandGroup.then(command: Command) = addSequential(command)

/** @see CommandGroup.addParallel */
public infix fun CommandGroup.and(command: Command) = addParallel(command)

/** @see CommandGroup.addSequential */
@JvmOverloads
public fun CommandGroup.addSequential(command: Command, timeout: Double = 0.0): CommandGroup {
    addSequential(command.mirror, timeout)
    return this
}

/** @see CommandGroup.addParallel */
@JvmOverloads
public fun CommandGroup.addParallel(command: Command, timeout: Double = 0.0): CommandGroup {
    addParallel(command.mirror, timeout)
    return this
}

/** @see edu.wpi.first.wpilibj.command.Command */
public abstract class Command @JvmOverloads constructor(
        timeout: Long = 0,
        unit: TimeUnit = TimeUnit.MILLISECONDS
) {
    internal val mirror = CommandMirror(this, timeout, unit)

    /** @see edu.wpi.first.wpilibj.command.Command.requires */
    public fun requires(subsystem: Subsystem) = mirror.requires(subsystem)

    /** @see edu.wpi.first.wpilibj.command.Command.start */
    public fun start() = mirror.start()

    /** @see edu.wpi.first.wpilibj.command.Command.cancel */
    public fun cancel() = mirror.cancel()

    /** @see edu.wpi.first.wpilibj.command.Command.requires */
    public open fun onCreate() = Unit

    /** @see edu.wpi.first.wpilibj.command.Command.execute */
    public abstract fun execute(): Boolean

    /** @see edu.wpi.first.wpilibj.command.Command.end */
    public open fun onDestroy() = Unit
}

private interface Requirable {
    fun requires(subsystem: Subsystem)
}

/** A mirror of WPILib's Command class. */
internal class CommandMirror(
        private val command: Command,
        timeout: Long,
        unit: TimeUnit
) : WpiLibCommand(unit.toSeconds(timeout).toDouble()), Requirable {
    @Suppress("RedundantOverride") // Needed for visibility override
    public override fun requires(subsystem: Subsystem) = super.requires(subsystem)

    override fun initialize() = command.onCreate()

    override fun isFinished(): Boolean = command.execute()

    override fun end() = command.onDestroy()

    override fun execute() = Unit
}
