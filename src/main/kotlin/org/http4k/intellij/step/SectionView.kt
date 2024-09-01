package org.http4k.intellij.step

import org.http4k.intellij.wizard.Answer
import org.http4k.intellij.wizard.Step
import java.awt.Color
import java.awt.Component.LEFT_ALIGNMENT
import java.awt.FlowLayout.LEFT
import javax.swing.BorderFactory
import javax.swing.BorderFactory.createEmptyBorder
import javax.swing.BorderFactory.createLineBorder
import javax.swing.BorderFactory.createTitledBorder
import javax.swing.BoxLayout
import javax.swing.BoxLayout.Y_AXIS
import javax.swing.JPanel
import javax.swing.border.TitledBorder.DEFAULT_POSITION

fun SectionView(section: Step.Section, parent: JPanel, onComplete: OnComplete): JPanel {
    val panel = JPanel().apply {
        layout = BoxLayout(this, Y_AXIS)
        border = BorderFactory.createCompoundBorder(
            createEmptyBorder(10, 10, 10, 10),
            createTitledBorder(
                createLineBorder(Color.GRAY, 1), "  ${section.label}  ",
                LEFT,
                DEFAULT_POSITION,
                font.deriveFont(16f)
            )
        )
        alignmentX = LEFT_ALIGNMENT
    }

    return panel.apply {
        add(ChildStepsView(section.steps, parent) {
            parent.remove(this)
            parent.revalidate()
            onComplete(listOf(Answer.Step(section.label, steps = it)))
        })
    }
}
