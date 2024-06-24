package hr.fer.oop.zi.zad3;

import java.util.Collection; 
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

public class Position {

    private final String name;
    private List<RequiredSkill> requiredSkills = new ArrayList<>();
    private Collection<Application> applications = new TreeSet<>(sortByPoints.thenComparing(sortById));
    // zasto ne radi sa zagradama ?!
    
    //thenComparing((a,b) -> a.getApplicant().getId() - b.getApplicant().getId())
    
    private static Comparator<Application> sortByPoints = new Comparator<Application>() {
		
		@Override
		public int compare(Application o1, Application o2) {
			double r = o2.getScore() - o1.getScore();
			if (r < 0) {
				return -1;
			} 
			if (r > 0) {
				return 1;
			}
			return 0;
		}
	};
	
	private static Comparator<Application> sortById = new Comparator<Application>() {
		
		@Override
		public int compare(Application o1, Application o2) {
			
			return o1.getApplicant().getId() - o2.getApplicant().getId();
		}
	};
    
    public Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Position addRequiredSkill(RequiredSkill skill) {
    	this.requiredSkills.add(skill);
        return this;
    }

    public void addApplication(Applicant applicant) {
        double score = 0;
        
        for (RequiredSkill r : requiredSkills) {
        	double realScore = applicant.getRequiredSkillScore(r.getName());
        	if (realScore < r.getMinimum()) {
        		score = 0;
        		break;
        		
        	} else {
        		score += realScore * r.getMultiplier();
        		
        	}
 
        }
        applications.add(new Application(applicant, score));
        
    }
    
    public Collection<Application> getApplications() {
        return applications;
    }

    public String summarizeApplications() {
        StringBuilder builder = new StringBuilder(this.name);
        builder.append(':');
        
        for (Application app : applications) {
        	builder.append(String.format("\n\t %s - ", app.getApplicant().getName()));
        	
        	if (app.getScore() == 0) {
        		builder.append("Did not meet requirements");
        		break;
        	} 
        	builder.append(String.format("%.2f", app.getScore()));
        }
        
        
        
        
        return builder.toString();
    }

}
