@file:Suppress("unused", "RedundantVisibilityModifier")
package org.sertain.command

import edu.wpi.first.wpilibj.command.Subsystem
import java.util.concurrent.TimeUnit

private typealias WpiLibCommand = edu.wpi.first.wpilibj.command.Command

public abstract class Command(timeout: Long = 0, unit: TimeUnit = TimeUnit.MILLISECONDS) {
    private val command = CommandMirror(this, timeout, unit)

    public fun requires(subsystem: Subsystem) = command.requires(subsystem)

    public fun start() = command.start()

    public fun cancel() = command.cancel()

    public open fun onCreate() = Unit

    public abstract fun execute(): Boolean

    public open fun onDestroy() = Unit
}

private interface Requirable {
    fun requires(subsystem: Subsystem)
}

private class CommandMirror(
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