import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class EnhancedMedicalChatbotGUI {

    private static HashMap<String, String> homeRemedies = new HashMap<>();
    private static HashMap<String, String> professionalAdvice = new HashMap<>();
    private static final String FILE_NAME = "user_interactions.txt";

    public static void main(String[] args) {
        // Load the medical knowledge base
        loadMedicalKnowledgeBase();

        // Set modern Look and Feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create JFrame for the GUI
        JFrame frame = new JFrame("Enhanced Medical AI Chatbot");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to AUG-MEDIX");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(Color.WHITE);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Disease selection drop-down
        JLabel label1 = new JLabel("Select a disease:");
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(label1, gbc);

        String[] diseases = {
            "Fever", "Headache", "Cold", "Cough", "Stomach Ache",
            "Migraine", "Back Pain", "Asthma", "Diabetes", "Hypertension",
            "Allergies", "Anxiety", "Depression", "Skin Rash", "Acid Reflux",
            "Fatigue", "Sinusitis", "Flu", "Gastroenteritis", "Arthritis",
            "Pneumonia", "Bronchitis", "Infection", "Sprain", "Tension Headache"
        };
        JComboBox<String> diseaseDropdown = new JComboBox<>(diseases);
        diseaseDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(diseaseDropdown, gbc);

        // Remedy or professional help drop-down
        JLabel label2 = new JLabel("Choose advice type:");
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(label2, gbc);

        String[] options = {"Home Remedy", "Professional Help"};
        JComboBox<String> remedyDropdown = new JComboBox<>(options);
        remedyDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(remedyDropdown, gbc);

        // Symptom input field
        JLabel symptomLabel = new JLabel("Additional symptoms:");
        symptomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(symptomLabel, gbc);

        JTextField symptomInput = new JTextField(15);
        symptomInput.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(symptomInput, gbc);

        // Severity scale input
        JLabel severityLabel = new JLabel("Severity (1-5):");
        severityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(severityLabel, gbc);

        JTextField severityInput = new JTextField(5);
        severityInput.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        mainPanel.add(severityInput, gbc);

        // Button to get advice
        JButton getAdviceButton = new JButton("Get Advice");
        getAdviceButton.setFont(new Font("Arial", Font.BOLD, 14));
        getAdviceButton.setBackground(new Color(0, 102, 204));
        getAdviceButton.setForeground(Color.WHITE);
        getAdviceButton.setToolTipText("Click to get medical advice based on your symptoms.");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        mainPanel.add(getAdviceButton, gbc);

        // Text area to display advice
        JTextArea adviceArea = new JTextArea(6, 30);
        adviceArea.setFont(new Font("Serif", Font.PLAIN, 14));
        adviceArea.setLineWrap(true);
        adviceArea.setWrapStyleWord(true);
        adviceArea.setEditable(false);
        adviceArea.setBackground(new Color(245, 245, 245));
        adviceArea.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(adviceArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(scrollPane, gbc);

        frame.add(mainPanel, BorderLayout.CENTER);

        // Load previous user interactions
        loadPreviousInteractions(adviceArea);

        // Button action listener
        getAdviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDisease = (String) diseaseDropdown.getSelectedItem();
                String selectedOption = (String) remedyDropdown.getSelectedItem();
                String additionalSymptoms = symptomInput.getText().trim().toLowerCase();
                int severity = 1;

                // Validate and get severity
                try {
                    severity = Integer.parseInt(severityInput.getText().trim());
                    if (severity < 1 || severity > 5) {
                        adviceArea.setText("Please enter a valid severity rating (1-5).");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    adviceArea.setText("Please enter a valid number for severity.");
                    return;
                }

                String advice;
                if (selectedOption.equals("Home Remedy")) {
                    advice = homeRemedies.get(selectedDisease);
                } else {
                    advice = professionalAdvice.get(selectedDisease);
                }

                // Add symptom-based advice
                if (!additionalSymptoms.isEmpty()) {
                    if (additionalSymptoms.contains("fatigue")) {
                        advice += "\nYou mentioned fatigue. Make sure to rest and hydrate.";
                    }
                    if (additionalSymptoms.contains("dizziness")) {
                        advice += "\nYou mentioned dizziness. Stay hydrated and rest.";
                    }
                    if (additionalSymptoms.contains("nausea")) {
                        advice += "\nFor nausea, consider ginger tea or small sips of water.";
                    }
                }

                // Add severity-based advice
                if (severity >= 4) {
                    advice += "\nYour symptoms seem severe. It's highly recommended to consult a healthcare provider.";
                } else if (severity == 1) {
                    advice += "\nYour symptoms seem mild. Home remedies might be sufficient, but keep an eye on them.";
                }

                adviceArea.setText(advice);

                // Save interaction
                saveInteraction(selectedDisease, selectedOption, additionalSymptoms, severity, advice);
            }
        });

        // Show the frame in the center of the screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadMedicalKnowledgeBase() {
        // Home remedies
        homeRemedies.put("Fever", "Rest, drink fluids, and take acetaminophen.");
        homeRemedies.put("Headache", "Stay hydrated, rest, and avoid bright screens.");
        homeRemedies.put("Cold", "Stay hydrated, rest, and take vitamin C.");
        homeRemedies.put("Cough", "Drink warm fluids, use cough drops.");
        homeRemedies.put("Stomach Ache", "Drink ginger tea, avoid spicy foods.");
        homeRemedies.put("Migraine", "Lie in a dark room, stay hydrated.");
        homeRemedies.put("Back Pain", "Apply heat or cold packs, gentle stretching.");
        homeRemedies.put("Asthma", "Use prescribed inhaler, avoid allergens.");
        homeRemedies.put("Diabetes", "Monitor blood sugar, follow your meal plan.");
        homeRemedies.put("Hypertension", "Reduce salt intake, exercise regularly.");
        homeRemedies.put("Allergies", "Avoid allergens, consider antihistamines.");
        homeRemedies.put("Anxiety", "Practice deep breathing, engage in relaxation techniques.");
        homeRemedies.put("Depression", "Talk to someone, engage in activities you enjoy.");
        homeRemedies.put("Skin Rash", "Keep the area clean and dry; consider over-the-counter creams.");
        homeRemedies.put("Acid Reflux", "Avoid spicy foods, eat smaller meals.");
        homeRemedies.put("Fatigue", "Rest and ensure proper hydration and nutrition.");
        homeRemedies.put("Sinusitis", "Steam inhalation can help relieve symptoms.");
        homeRemedies.put("Flu", "Stay hydrated, rest, and consider over-the-counter flu medications.");
        homeRemedies.put("Gastroenteritis", "Stay hydrated, consider bland foods.");
        homeRemedies.put("Arthritis", "Gentle exercise and hot/cold therapy may help.");
        homeRemedies.put("Pneumonia", "Rest, drink fluids, and consider over-the-counter medications.");
        homeRemedies.put("Bronchitis", "Stay hydrated, consider honey in warm water.");
        homeRemedies.put("Infection", "Apply warm compresses and rest.");
        homeRemedies.put("Sprain", "Rest, ice the area, and elevate.");
        homeRemedies.put("Tension Headache", "Relaxation techniques and hydration may help.");

        // Professional advice
        professionalAdvice.put("Fever", "Consult a doctor if the fever persists.");
        professionalAdvice.put("Headache", "Seek medical attention for severe headaches.");
        professionalAdvice.put("Cold", "Consult a physician if symptoms worsen.");
        professionalAdvice.put("Cough", "See a doctor if coughing lasts more than a week.");
        professionalAdvice.put("Stomach Ache", "Consult a doctor for severe abdominal pain.");
        professionalAdvice.put("Migraine", "Consult a neurologist for chronic migraines.");
        professionalAdvice.put("Back Pain", "Seek advice from an orthopedist.");
        professionalAdvice.put("Asthma", "Regular check-ups with an allergist are crucial.");
        professionalAdvice.put("Diabetes", "Regular check-ups with an endocrinologist are essential.");
        professionalAdvice.put("Hypertension", "Consult a cardiologist for management.");
        professionalAdvice.put("Allergies", "Consult an allergist for persistent allergies.");
        professionalAdvice.put("Anxiety", "Consider talking to a mental health professional.");
        professionalAdvice.put("Depression", "Consult a therapist for support.");
        professionalAdvice.put("Skin Rash", "See a dermatologist for persistent rashes.");
        professionalAdvice.put("Acid Reflux", "Consult a gastroenterologist if symptoms persist.");
        professionalAdvice.put("Fatigue", "Seek medical advice for unexplained fatigue.");
        professionalAdvice.put("Sinusitis", "Consult an ENT specialist for chronic sinus issues.");
        professionalAdvice.put("Flu", "Consult a physician if symptoms are severe.");
        professionalAdvice.put("Gastroenteritis", "Seek medical attention if dehydration occurs.");
        professionalAdvice.put("Arthritis", "Regular check-ups with a rheumatologist are advisable.");
        professionalAdvice.put("Pneumonia", "Seek immediate medical attention for pneumonia symptoms.");
        professionalAdvice.put("Bronchitis", "Consult a doctor if symptoms persist for over a week.");
        professionalAdvice.put("Infection", "Seek medical advice if the infection worsens.");
        professionalAdvice.put("Sprain", "Consult a doctor if pain persists or worsens.");
        professionalAdvice.put("Tension Headache", "Seek advice if headaches continue despite treatment.");
    }

    private static void loadPreviousInteractions(JTextArea adviceArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            StringBuilder previousInteractions = new StringBuilder("Previous Interactions:\n\n");
            while ((line = reader.readLine()) != null) {
                previousInteractions.append(line).append("\n");
            }
            adviceArea.setText(previousInteractions.toString());
        } catch (IOException e) {
            adviceArea.setText("No previous interactions found.");
        }
    }

    private static void saveInteraction(String disease, String option, String symptoms, int severity, String advice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("Disease: " + disease);
            writer.write(", Option: " + option);
            writer.write(", Symptoms: " + symptoms);
            writer.write(", Severity: " + severity);
            writer.write(", Advice: " + advice);
            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error saving interaction: " + e.getMessage());
        }
    }
}
