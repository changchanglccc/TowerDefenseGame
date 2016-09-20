package protocol;

/**
 * interface for refreshing  data panel
 * @author yongpinggao
 * @since 3/16/16.
 * @version 2.0 
 */
public interface DrawingDataPanelDelegate {

  /**
   * reload Wave DataView
   * @param waveNum wave number 
   */
    void reloadWaveDataView (int waveNum);
   /**
    * reload Balance DataView
    * @param balance player balancee
   */
    void reloadBalanceDataView(double balance);

    /**S
     * reload Coin DataView
     * @param  coin coin
     */
    void reloadCoinDataView(int coin);
 
    /**
     * reload Info DataView
     * @param info game info
     */
    void reloadInfoDataView(String info);
}
