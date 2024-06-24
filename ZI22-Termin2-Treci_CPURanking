import java.util.Comparator;

public class CPURanking {
	
	private CPURanking(CPU cpu) {

	}
	
	public static Comparator<CPU> hasHyperthreadingFirst() {
	
		return (cp1, cp2) -> {
			if (cp1.hasHyperthreading() == cp2.hasHyperthreading()) {
				return 0;
				
			} else if (cp1.hasHyperthreading() && !cp2.hasHyperthreading()) {
				return -1;
			} else {
				return 1;
			}
		};
	}
	
	public static Comparator<CPU> withinBudgetFirst(int budget) {
		return (a, b) -> {
			if (a.getCost() > budget && b.getCost() > budget) {
				return 0;
				
			} else if (a.getCost() < budget) {
				return -1;
				
			} else {
				return 1;
			}
		};
	}
	
	public static Comparator<CPU> byPotentialSpeed() {
		
		Comparator<CPU> clockSpeedComparator = new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				return Double.compare(o2.getClockSpeed(), o1.getClockSpeed());
			}
		};
		
		Comparator<CPU> coresComparator = new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				return Integer.compare(o2.getCores(), o1.getCores());
			}
		};
		
		
		return new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				int r = clockSpeedComparator.thenComparing(coresComparator).thenComparing(hasHyperthreadingFirst())
						.compare(o1, o2);
				return r;
			}
		};
	}
	
	public static Comparator<CPU> byCostEfficiency() {
		
	return new Comparator<CPU>() {
		double zbroj1 = 0, zbroj2 = 0, efficiency1, efficiency2;
		
		@Override
		public int compare(CPU o1, CPU o2) {
			zbroj1 += o1.getClockSpeed() * o1.getCores();
			if (o1.hasHyperthreading()) {
				zbroj1 *= 1.2;
			}
			
			zbroj2 += o2.getClockSpeed() * o2.getCores();
			if (o2.hasHyperthreading()) {
				zbroj2 *= 1.2;
			}
			
			efficiency1 = zbroj1 / o1.getCost();
			
			efficiency2 = zbroj2 / o2.getCost();
			
			int r = 0;
			if (efficiency1 > efficiency2) {
				r = -1;
				
			} else if (efficiency1 < efficiency2) {
				r = 1;
			}
			
			return r;
		}
	};
	}	
	
	public static Comparator<CPU> byAffordabilityAndEfficiency(int budget) {
		Comparator<CPU> byName = (cpu1, cpu2) -> {
			return cpu1.getName().compareTo(cpu2.getName());
		};
		
		return new Comparator<CPU>() {
			
			@Override
			public int compare(CPU o1, CPU o2) {
				int r = withinBudgetFirst(budget).thenComparing(byCostEfficiency())
						.thenComparing(byPotentialSpeed()).thenComparing(byName)
						.compare(o1, o2);
				return r;
			}
		};
	}
}
