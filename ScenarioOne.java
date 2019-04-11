import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ScenarioOne {

    private int chance;
    private int outcome;
    private int totalBrier;
    private ArrayList<Double> forecasts = new ArrayList<>();
    private ArrayList<Double> score = new ArrayList<>();

    public void sOne() {
        for (int i = 0; i < 3; i++ ) {
            System.out.println("Kommer regeringen få igenom sin vårändringsbudget 1 juni 2019? Ange en procentuell chans mellan 0-100");
            Scanner sc = new Scanner(System.in);
            Double forecast = sc.nextDouble();
            while (forecast < 0 || forecast > 100) {
                System.out.println("Din uppskattning måste va mellan 0-100");
                forecast = sc.nextDouble();
            }
            forecast = forecast/100;
            forecasts.add(forecast);
            if (i < 2){
                newspaper();
            }
        }
        calculateOutcome();
        calculateBrier();
        if (outcome == 0) {
            System.out.println("Regeringen fick inte genom sin budget.");
        } else if (outcome == 1) {
            System.out.println("Regeringen fick igenom sin budget!");
        }

    }

    private void calculateOutcome() {

        Random r = new Random();
        int n = r.nextInt(10);
        n += 1;
        int out = n-chance;

        if (out < 5) {
            outcome = 0;
        } else if (out < 10) {
            Random r1 = new Random();
            int i = r1.nextInt(6);
            i += 1;
            if (i < 3) {
                outcome = 0;
            } else {
                outcome = 1;
            }
        } else if (out < 12) {
            Random r1 = new Random();
            int i = r1.nextInt(8);
            i += 1;
            if (i < 3) {
                outcome = 0;
            } else {
                outcome = 1;
            }
        } else if (out > 11) {
            outcome = 1;
        }


    }

    private void calculateBrier(){
        System.out.println("Calculate brier");
        DecimalFormat df = new DecimalFormat("#.##");
        for (Double i: forecasts) {
            if (outcome == 1) {
               Double oc = i-1;
               Double noc = 1-i;
               Double moc = oc * oc;
               Double mnoc = noc * noc;
               Double bs = moc + mnoc;
               System.out.println("Brier score: "+ df.format(bs));
               score.add(bs);
            } else if (outcome == 0) {
                System.out.println("forecast: " + i);
                Double woc = 1-i;
                Double twoc = woc-1;
                Double moc = twoc * twoc;
                Double noc = 1-i;
                Double mnoc = noc * noc;
                Double bs = moc + mnoc;
                System.out.println("Brier score: " + df.format(bs));
                score.add(bs);
            }
        }
    }

    private void newspaper() {
        System.out.println("EXTRA! EXTRA!");

        Random rand = new Random();
        int n = rand.nextInt(5);
        n += 1;

        if (n == 1) {
            newsOne();
        } else if (n == 2 ){
            newsTwo();
        } else if (n == 3 ){
            newsThree();
        } else if (n == 4 ){
            newsFour();
        } else if (n == 5 ){
            newsFive();
        }


    }

    private void newsOne() {
        String s = "Allan Widman (L) är mycket kritisk till uteblivna satsningar på försvarsmakten i regeringens vårändringsbudget";
        System.out.println(s);
        chance --;

    }

    private void newsTwo() {
        String s = "Annie Lööf försvarar vårbudgeten: Centerpartiet är mycket nöjda med att höginkomstagare i Sverige får sänkt skatt!";
        System.out.println(s);
        chance = chance+2;
        System.out.println(chance);
    }

    private void newsThree() {
        String s = "Rosornas krig bryter ut, Göteborgs arbetarkommun uppmanar sina riksdagskandidater att rösta på Jimmie Åkessons budget.";
        System.out.println(s);
        chance = chance-3;

    }

    private void newsFour() {
        String s = "Kristersson stödjer regeringens budget: En sån här blå budget har vi inte sett sedan Gösta Bohman!";
        System.out.println(s);
        chance = chance + 3;
    }

    private void newsFive() {
        String s = "Vänsterpartiet tänker rösta mot regeringens vårändringsbudget";
        System.out.println(s);
        chance--;

    }
}
