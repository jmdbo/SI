package si.api.utils;

/**
 * Created by João on 11/11/2014.
 */
public class WorldState {

    public Instruction SimpleInstruction;
    public Instruction ComplexInstruction;
    private HwByte state;

    public WorldState(Instruction Simple, Instruction Complex, HwByte state){
        this.SimpleInstruction = Simple;
        this.ComplexInstruction = Complex;
        this.state = new HwByte(state.getBits());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof WorldState)) return false;
        WorldState worldState = (WorldState) obj;
        if(!worldState.SimpleInstruction.equals(this.SimpleInstruction))
            return false;

        return true;
    }
}
