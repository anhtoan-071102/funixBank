/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dictionary.dictionaryapp;

/**
 *
 * @author ADMIN
 */
import java.util.Locale;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class TextToSpeech {

    private Synthesizer synthesizer;
    private boolean isSynthesizerAllocated = false;

    public TextToSpeech() throws EngineException {
        try {
            // Set properties for FreeTTS voice
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            System.setProperty("javax.speech.synthesis.voice", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            // Register Engine Central
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            // Create a synthesizer for US English if not already allocated
            if (!isSynthesizerAllocated) {
                SynthesizerModeDesc desc = new SynthesizerModeDesc(Locale.US);
                synthesizer = Central.createSynthesizer(desc);
                isSynthesizerAllocated = true;
            }
        } catch (IllegalArgumentException | EngineException e) {
            e.printStackTrace();
        }
    }

    public void speak(String text) {
        try {
            if (!isSynthesizerAllocated) {
                throw new IllegalStateException("Synthesizer has not been allocated. Call TextToSpeech constructor first.");
            }

            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (IllegalArgumentException | InterruptedException | AudioException | EngineException e) {
            e.printStackTrace();
        } 
    }
    
    public void closeTTS() {
        try {
            if (synthesizer != null && isSynthesizerAllocated) {
                synthesizer.deallocate();
                isSynthesizerAllocated = false;
            }
        } catch (EngineException e) {
            e.printStackTrace();
        }
    }
}


