import java.util.Random;

public class RandomQuoteGenerator {
    // Array of quotes, generated by chatGpt
    String[] quotes = {
            "All our dreams can come true, if we have the courage to pursue them. - Walt Disney",
            "The secret of getting ahead is getting started. - Mark Twain",
            "The best time to plant a tree was 20 years ago. The second best time is now. - Chinese Proverb",
            "Only the paranoid survive. - Andy Grove",
            "It’s hard to beat a person who never gives up. - Babe Ruth",
            "I wake up every morning and think to myself, ‘how far can I push this company in the next 24 hours.’ - Leah Busque",
            "If people are doubting how far you can go, go so far that you can’t hear them anymore. - Michele Ruiz",
            "Write it. Shoot it. Publish it. Crochet it, sauté it, whatever. MAKE. - Joss Whedon",
            "Everything you can imagine is real. - Pablo Picasso",
            "Do one thing every day that scares you. - Eleanor Roosevelt",
            "It’s no use going back to yesterday, because I was a different person then. - Lewis Carroll",
            "Smart people learn from everything and everyone, average people from their experiences, stupid people already have all the answers. - Socrates",
            "Do what you feel in your heart to be right – for you’ll be criticized anyway. - Eleanor Roosevelt",
            "Happiness is not something ready made. It comes from your own actions. - Dalai Lama XIV",
            "Whatever you are, be a good one. - Abraham Lincoln",
            "The same boiling water that softens the potato hardens the egg. It’s what you’re made of. Not the circumstances. - Unknown",
            "If we have the attitude that it’s going to be a great day it usually is. - Catherine Pulsifier",
            "You can either experience the pain of discipline or the pain of regret. The choice is yours. - Unknown",
            "Impossible is just an opinion. - Paulo Coelho",
            "Your passion is waiting for your courage to catch up. - Isabelle Lafleche",
            "Magic is believing in yourself. If you can make that happen, you can make anything happen. - Johann Wolfgang Von Goethe",
            "If something is important enough, even if the odds are stacked against you, you should still do it. - Elon Musk",
            "Hold the vision, trust the process. - Unknown",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "People who wonder if the glass is half empty or full miss the point. The glass is refillable. - Unknown",
            "Just another Magic Monday - Unknown",
            "One day or day one. You decide. - Unknown",
            "It’s Monday… time to motivate and make dreams and goals happen. Let’s go! - Heather Stillufsen",
            "It was a Monday and they walked on a tightrope to the sun. - Marcus Zusak",
            "Goodbye blue Monday. - Kurt Vonnegut",
            "When life gives you Monday, dip it in glitter and sparkle all day. - Ella Woodword",
            "Monday c’est Mon Day",
            "All Motivation Mondays need are a little more coffee and a lot more mascara",
            "I’m alive, motivated and ready to slay the day #MONSLAY",
            "No one is to blame for your future situation but yourself. If you want to be successful, then become 'Successful.' - Jaymin Shah",
            "Things may come to those who wait, but only the things left by those who hustle. - Abraham Lincoln",
            "Everything comes to him who hustles while he waits. - Thomas Edison",
            "Invest in your dreams. Grind now. Shine later. - Unknown",
            "Hustlers don’t sleep, they nap. - Unknown",
            "Asiyeni A'ba.lé alowe Mkati. -Spe🍄",
            "Greatness only comes before hustle in the dictionary. - Ross Simmonds",
            "Without hustle, talent will only carry you so far. - Gary Vaynerchuk",
            "Work like there is someone working twenty four hours a day to take it away from you. - Mark Cuban",
            "Hustle in silence and let your success make the noise. - Unknown",
            "We are what we repeatedly do. Excellence, then, is not an act, but a habit. - Aristotle",
            "If you’re offered a seat on a rocket ship, don’t ask what seat! Just get on. - Sheryl Sandberg",
            "If you hear a voice within you say ‘you cannot paint,’ then by all means paint and that voice will be silenced. - Vincent Van Gogh",
            "How wonderful it is that nobody need wait a single moment before starting to improve the world. - Anne Frank"
    };

    // getter for quote
    public String getQoute() {
        //getting them randomly
        Random random = new Random();
        int randomIndex = random.nextInt(quotes.length);
        String randomQuote = quotes[randomIndex];
        return randomQuote;
    }

}
