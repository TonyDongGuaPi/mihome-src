package com.xiaomi.ai;

import com.xiaomi.ai.api.common.Instruction;

public interface InstructionListener {
    void a(Instruction[] instructionArr, SpeechResult speechResult);
}
