@file:Suppress("unused", "RedundantVisibilityModifier")
@file:JvmName("Joysticks")
package org.sertain.hardware

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import org.sertain.command.Command
import org.sertain.command.CommandBridgeMirror

/** @return [Joystick.getThrottle], scaled to positive values (0..1) */
// Minus because `throttle` is 1 when at the bottom...sigh WPI
public val Joystick.scaledThrottle get() = (throttle - 1) / 2

/**
 * @param button the number of the button to listen for
 * @param command the command to execute
 * @see JoystickButton.whenActive
 */
public fun GenericHID.whenActive(button: Int, command: CommandBridgeMirror) {
    JoystickButton(this, button).whenActive(command.mirror)
}

/**
 * @param button the number of the button to listen for
 * @param command the command to execute
 * @see JoystickButton.whileActive
 */
public fun GenericHID.whileActive(button: Int, command: CommandBridgeMirror) {
    JoystickButton(this, button).whileActive(command.mirror)
}

/** @see [whenActive] */
public inline fun GenericHID.whenActive(
        button: Int,
        crossinline block: () -> Boolean
) = whenActive(button, object : Command() {
    override fun execute() = block()
})

/** @see [whileActive] */
public inline fun GenericHID.whileActive(
        button: Int,
        crossinline block: () -> Boolean
) = whileActive(button, object : Command() {
    override fun execute() = block()
})
