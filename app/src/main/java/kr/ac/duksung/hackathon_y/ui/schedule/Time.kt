package kr.ac.duksung.hackathon_y.ui.schedule

data class Time (
	val hour: Int,
	val min: Int
) {
	override fun toString(): String {
		return String.format("%02d:%02d", hour, min)
	}
}