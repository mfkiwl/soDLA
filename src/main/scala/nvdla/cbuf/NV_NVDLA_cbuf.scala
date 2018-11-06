package nvdla

import chisel3._

class NV_NVDLA_cbuf(implicit val conf: cbufConfiguration) extends Module {
 
  //csb interface  
  val io = IO(new Bundle {

    //clock
    val nvdla_core_clk = Input(Clock())
    val nvdla_core_rstn = Input(Bool())

    val pwrbus_ram_pd = Input(UInt(32.W))

    //: for(my $i=0; $i<CBUF_WR_PORT_NUMBER ; $i++) {
    //: print qq(
    //: input[CBUF_ADDR_WIDTH-1:0] cdma2buf_wr_addr${i}; //|< i
    //: input[CBUF_WR_PORT_WIDTH-1:0] cdma2buf_wr_data${i}; //|< i
    //: input cdma2buf_wr_en${i};   //|< i
    //: input[CBUF_WR_BANK_SEL_WIDTH-1:0] cdma2buf_wr_sel${i}; //|< i
    //: )
    //: }


    val cdma2buf_wr_addr = Input(Vec(conf.CBUF_WR_PORT_NUMBER, UInt(conf.CBUF_ADDR_WIDTH.W)))
    val cdma2buf_wr_data = Input(Vec(conf.CBUF_WR_PORT_NUMBER, UInt(conf.CBUF_WR_PORT_WIDTH.W)))
    val cdma2buf_wr_en = Input(Vec(conf.CBUF_WR_PORT_NUMBER, Bool()))
    val cdma2buf_wr_sel = Input(Vec(conf.CBUF_WR_PORT_NUMBER, UInt(conf.CBUF_WR_BANK_SEL_WIDTH.W))) 

    val sc2buf_dat_rd_en = Input(Bool())     /* data valid */
    val sc2buf_dat_rd_addr = Input(UInt(conf.CBUF_ADDR_WIDTH.W))
    val sc2buf_dat_rd_shift = Input(UInt(conf.CBUF_RD_DATA_SHIFT_WIDTH.W))
    val sc2buf_dat_rd_next1_en = Input(Bool())
    val sc2buf_dat_rd_next1_addr = Input(UInt(conf.CBUF_ADDR_WIDTH.W))
    val sc2buf_dat_rd_valid = Output(Bool())               /* data valid */
    val sc2buf_dat_rd_data = Output(UInt(CBUF_RD_PORT_WIDTH.W))

    val sc2buf_wt_rd_en = Input(Bool()) /* data valid */
    val sc2buf_wt_rd_addr = Input(UInt(conf.CBUF_ADDR_WIDTH.W))
    val sc2buf_wt_rd_valid = Output(Bool())
    val sc2buf_wt_rd_data = Output(UInt(CBUF_RD_PORT_WIDTH.W))

    if(conf.CBUF_WEIGHT_COMPRESSED){
        val sc2buf_wmb_rd_en = Input(Bool())
        val sc2buf_wmb_rd_addr = Input(UInt(conf.CBUF_ADDR_WIDTH.W))
        val sc2buf_wmb_rd_valid = Output(Bool())
        val sc2buf_wmb_rd_data = Output(UInt(CBUF_RD_PORT_WIDTH.W))
    }
  })

 //////////step1:write handle
//decode write address to sram
//: my $bank_slice= CBUF_BANK_SLICE;  #address part for select bank
//: for(my $j=0; $j<CBUF_BANK_NUMBER ; $j++){
//:     for(my $k=0; $k<CBUF_RAM_PER_BANK ; $k++){
//:         my $kmod2 = $k%2;
//:         my $kmod4 = $k%4; 
//:         for(my $i=0; $i<CBUF_WR_PORT_NUMBER ; $i++){
//:         if((CBUF_BANK_RAM_CASE==0)||(CBUF_BANK_RAM_CASE==2)||(CBUF_BANK_RAM_CASE==4)){
//:         print qq(
//:     wire  bank${j}_ram${k}_wr${i}_en_d0 = cdma2buf_wr_en${i}&&(cdma2buf_wr_addr${i}[${bank_slice}]==${j}) &&(cdma2buf_wr_sel${i}[${k}]==1'b1);  );
//:             }
//:         if(CBUF_BANK_RAM_CASE==1){
//:         print qq(
//:     wire  bank${j}_ram${k}_wr${i}_en_d0 = cdma2buf_wr_en${i}&&(cdma2buf_wr_addr${i}[${bank_slice}]==${j})&&(cdma2buf_wr_addr${i}[0]==${k});  );
//:             }
//:         if(CBUF_BANK_RAM_CASE==3){
//:         print qq(
//:     wire  bank${j}_ram${k}_wr${i}_en_d0 = cdma2buf_wr_en${i}&&(cdma2buf_wr_addr${i}[${bank_slice}]==${j})&&(cdma2buf_wr_addr${i}[0]==${k})&&(cdma2buf_wr_sel${i}[${kmod2}]==1'b1 );  );
//:             }
//:         if(CBUF_BANK_RAM_CASE==5){
//:         print qq(
//:     wire  bank${j}_ram${k}_wr${i}_en_d0 = cdma2buf_wr_en${i}&&(cdma2buf_wr_addr${i}[${bank_slice}]==${j})&&(cdma2buf_wr_addr${i}[0]==${k})&&(cdma2buf_wr_sel${i}[${kmod4}]==1'b1 );  );
//:             }
//:         }
//:     }
//: }



}


  