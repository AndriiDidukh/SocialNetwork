package ua.entity.enums;

public enum DayOfBirth {
	day1(1), day2(2), day3(3), day4(4), day5(5), day6(6), day7(7), day8(8), day9(9), day10(10), day11(11), day12(12), day13(13), day14(14), day15(15), day16(16), day17(17), day18(18), day19(19), day20(
			20), day21(21), day22(22), day23(23), day24(24), day25(25), day26(26), day27(27), day28(28), day29(29), day30(30), day31(31);

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private DayOfBirth(int id) {
		this.id = id;
	}

}
