package nvdla

import chisel3.iotesters.{PeekPokeTester, Driver, ChiselFlatSpec}

class NV_NVDLA_IS_BC_pipeTests(c: NV_NVDLA_IS_BC_pipe) extends PeekPokeTester(c) {
  for(t <- 0 to 9){
    
    // val wdata = rnd.nextInt(1<<32)
    // poke(c.io.vi, 1)
    // poke(c.io.ri, 0)
    // poke(c.io.di, wdata)
    

    // step(1)

    // expect(c.io.vo, 1)
    // expect(c.io.ro, 0)
    // expect(c.io.dout, wdata)

  }
}


class NV_NVDLA_IS_BC_pipeTester extends ChiselFlatSpec {
  behavior of "NV_NVDLA_IS_BC_pipe"
  backends foreach {backend =>
    it should s"... $backend" in {
      Driver(() => new NV_NVDLA_IS_BC_pipe(32))(c => new NV_NVDLA_IS_BC_pipeTests(c)) should be (true)
    }
  }
}