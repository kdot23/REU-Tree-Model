sim_location = "~/Documents/REU/results_170802/Simulator/test_"
sim2_loc = "~/Documents/REU/results_170802/Simulator2/test_"
sim3_loc = "~/Documents/REU/results_170802/Simulator3/test_"
sim4_loc = "~/Documents/REU/results_170802/Simulator4/test_"

plotResults <- function(location){
  sim_res = data.frame()
  
  for (testNum in 1:500){
    s = paste(location,testNum,".txt", sep = "")

    test_1 <- read_csv(s, n_max=11, col_names = c("V1","V2","V3","V4","HV1","HV2","
                                           HV3","HV4","H1","H2","H3","H4"))

    fr = as.data.frame(test_1)
    for (y in 1:11){
      sim_res[testNum,3*y - 2] = sum(fr[y,1:4])
      sim_res[testNum,3*y -1] = sum(fr[y,5:8])
      sim_res[testNum,3*y] = sum(fr[y,9:12])
    }
  }
  colnames(sim_res) = c("V0","HV0","H0","V10","HV10","H10","V20","HV20","H20",
                        "V30","HV30","H30","V40","HV40","H40","V50","HV50","H50",
                        "V60","HV60","H60", "V70","HV70","H70", "V80","HV80","H80",
                        "V90","HV90","H90", "V100","HV100","H100")

  k = c()
  for (j in 1:ncol(sim_res)){
    k[j] = mean(sim_res[[j]])
  }
  k2 = matrix(k,ncol=3,nrow=11,byrow=T)
  k3 = t(k2)
  barplot(k3,beside=T)

return (sim_res)
}
