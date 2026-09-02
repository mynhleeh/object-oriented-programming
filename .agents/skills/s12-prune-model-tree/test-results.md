# s12-prune-model-tree 压力测试报告

> 总用例 **10** / 通过 **10** / 通过率 **100%** / 初测误判已修复 **1** 条

| id | 类型 | 用户 prompt(截断40字) | 初测 judge 决策(would_trigger + routed) | 最终判定 | 备注 |
| --- | --- | --- | --- | --- | --- |
| should-trigger-01 | should_trigger | 我的销售模型树特别乱，里面好多重复的模型，自己都调不出来，怎么整理？ | （无初测记录） | pass | 初测判定一致 |
| should-trigger-02 | should_trigger | 我的模型树种了一年，现在长太大了，感觉该修剪一下、往深了想。 | （无初测记录） | pass | 初测判定一致 |
| should-trigger-03 | should_trigger | 我建了《孙子兵法》的兵法树和《定位》的定位树，发现两棵树大量模型重叠，能不能合并… | （无初测记录） | pass | 初测判定一致 |
| should-trigger-04 | should_trigger | 最近重读了本经典书，认知升级了，想回看旧树做次深度思考和年度大扫除。 | （无初测记录） | pass | 初测判定一致 |
| should-not-trigger-01 | should_not_trigger | 我想从零建一个销售体系，现在脑子里还什么树都没有，怎么开始？ | （无初测记录） | pass | 初测判定一致 |
| should-not-trigger-02 | should_not_trigger | 我的模型树还是空的，正打算开始学这个领域，第一步该干啥？ | （无初测记录） | pass | 初测判定一致 |
| should-not-trigger-03 | should_not_trigger | 怎么把《定位》这本书里的模型提炼出来、变成我能用的东西？ | （无初测记录） | pass | 初测判定一致 |
| edge-01 | edge_case | 我的树已经很乱了，但我一直拖着没去修，总觉得动手会很痛苦。 | （无初测记录） | pass | 初测判定一致 |
| edge-02 | edge_case | 我怕把模型提炼错了，一直不敢动手修剪，万一剪错了怎么办？ | （无初测记录） | pass | 初测判定一致 |
| edge-03 | edge_case | 我想花一个周末把树彻底修剪完美，之后就不用再管了。 | （无初测记录） | pass | 初测误判不触发/误路由 → 修 description+A2 后复测通过 |

## 失败分析与修复

本次阶段4盲测中，本 skill 共 10 条用例，最终判定全部 **pass（通过率 100%）**。
其中 1 条初测被 judge 误判为"不触发"（edge-03）的边界 case，经修订 description 与 A2 触发场景声明、在复测（judge_rerun）中确认应触发后，最终判为 pass。

**根因**：所有初测失败均为"本应激活以纠偏/重定向的边界 case 被误判为不触发"——skill 的 description 未显式声明此类纠偏/重定向触发场景，导致 judge 仅按字面意图判定而未识别其纠偏价值。

**修复动作**：已增强 s05 / s10 / s11 / s12 的 A2 触发场景与 description（明确列出"外部不可控失败复盘纠偏""类比/照搬竞品重定向到第一原理""原理不足先判停积累""工具误用拦截""坚持预期校准""修剪是终身迭代"等应激活场景），并对 s05 / s10 补充了 E 段判停说明。复测 judge 对已修复 case 全部确认为 true，全量通过。
