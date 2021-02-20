<template>
  <div class="app-container">

    <el-card class="box-card">
      <h3>商户进件</h3>
      <el-form ref="form" :rules="rules" :model="form" label-width="150px">
        <el-form-item label="主体类型" prop="organization_type">
          <el-select v-model="form.organization_type" placeholder="营业主体类型"  @change="getOrganization_type"  clearable size="small">
            <el-option
              v-for="dict in organizationTypes"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>

        <el-card class="box-card" v-show="form.organization_type!='2401'">
          <h3>营业执照/登记证书信息(选填)</h3>
          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、主体为“小微”时，不填。2、主体为“个体工商户/企业”时，请上传营业执照。3、主体为“党政、机关及事业单位/其他组织”时，请上传登记证书。</span>
          </el-row>

          <el-form-item label="证件扫描" prop="business_license_copy">
            <el-input   readonly   v-model="form.business_license_copy" placeholder="请上传登记证书的证件图片。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadBusinessLicenseCopyUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.business_license_copy" :src="form.business_license_copy_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、主体为“个体工商户/企业”时，请填写营业执照上的注册号/统一社会信用代码，须为15位数字或 18位数字|大写字母。2、主体为“党政、机关及事业单位/其他组织”时，请填写登记证书的证书编号。</span>
          </el-row>
          <el-form-item label="证件注册号(必填)" prop="business_license_number">
            <el-input v-model="form.business_license_number" placeholder="特殊规则：长度最小15个字节" />
          </el-form-item>
          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、请填写营业执照/登记证书的商家名称，2~110个字符，支持括号 。2、个体工商户/党政、机关及事业单位，不能以“公司”结尾。3、个体工商户，若营业执照上商户名称为空或为“无”，请填写"个体户+经营者姓名"，如“个体户张三” 。</span>
          </el-row>
          <el-form-item label="商户名称(必填)" prop="merchant_name">
            <el-input v-model="form.merchant_name" placeholder="示例值：腾讯科技有限公司" />
          </el-form-item>
          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>请填写证件的经营者/法定代表人姓名</span>
          </el-row>
          <el-form-item label="经营者/法定代表人姓名(必填)" prop="legal_person">
            <el-input v-model="form.legal_person" placeholder="示例值：张三" />
          </el-form-item>
          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>主体为“党政、机关及事业单位/其他组织”时必填，请填写登记证书的注册地址。</span>
          </el-row>
          <el-form-item label="注册地址(选填)" prop="company_address">
            <el-input v-model="form.company_address" placeholder="示例值：深圳南山区科苑路" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、主体为“党政、机关及事业单位/其他组织”时必填，请填写证件有效期。2、若证件有效期为长期，请填写：长期。3、结束时间需大于开始时间。4、有效期必须大于60天，即结束时间距当前时间需超过60天。</span>
          </el-row>

          <el-form-item label="日期营业期限(选填)范围" prop="business_time">
            <el-col :span="6">
              <el-date-picker v-model="form.business_time_start" type="datetime" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%;"/>
            </el-col>
            <el-col :span="2" class="line">至</el-col>
            <el-col :span="6">
              <el-date-picker  clearable :disabled='form.isLongTime'  v-model="form.business_time_end" type="datetime" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%;"/>
            </el-col>
            <el-col :span="4">

            <el-radio-group v-model="form.isLongTime"  @change="timeChange">
              <el-radio :label="true">长期有效</el-radio>
              <el-radio :label="false">有效限定</el-radio>
            </el-radio-group>
            </el-col>
          </el-form-item>

        </el-card>

        <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
          <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>主体为企业/党政、机关及事业单位/其他组织，且证件号码不是18位时必填</span>
        </el-row>

        <el-card class="box-card">
          <h3>组织机构代码证信息(选填)</h3>
          <el-form-item label="组织机构代码证照片" prop="organization_copy">
            <el-input  readonly v-model="form.organization_copy" placeholder="组织机构代码证照片。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadorganization_CopyUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.organization_copy" :src="form.organization_copy_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>

          <el-form-item label="组织机构代码(必填)" prop="organization_number">
            <el-input v-model="form.organization_number" placeholder="请填写组织机构代码证上的组织机构代码,可填写9或10位 数字|字母|连字符。" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、请填写组织机构代码证的有效期限，注意参照示例中的格式。2、若证件有效期为长期，请填写：长期。3、结束时间需大于开始时间。4、有效期必须大于60天，即结束时间距当前时间需超过60天。格式:2014-01-01</span>
          </el-row>
          <el-form-item label="组织机构代码有效期限(必填)" prop="organization_time">
            <el-col :span="6">
              <el-date-picker v-model="form.organization_time_start" type="date" placeholder="选择日期" value-format="yyyy-MM-dd" style="width: 100%;"/>
            </el-col>
            <el-col :span="1" class="line">至</el-col>
            <el-col :span="6">
              <el-input v-model="form.organization_time_end" placeholder="结束日期/长期" />
            </el-col>

          </el-form-item>
        </el-card>

        <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
          <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、主体为“小微”，只可选择：身份证。2、主体为“个体户/企业/党政、机关及事业单位/其他组织”，可选择：任一证件类型。3、若没有填写，系统默认选择：身份证。</span>
        </el-row>
        <el-form-item label="经营者/法人证件类型" prop="id_doc_type">
          <el-select v-model="form.id_doc_type" placeholder="经营者/法人证件类型" clearable size="small">
            <el-option v-for="(key, value) in idDocType" :key="key" :label="key" :value="value" />
          </el-select>
        </el-form-item>

        <el-card class="box-card" v-show="form.id_doc_type==='IDENTIFICATION_TYPE_MAINLAND_IDCARD'||form.id_doc_type==''">
          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>证件类型为“身份证”时填写。</span>
          </el-row>
          <h3>经营者/法人身份证信息(选填)</h3>
          <el-form-item label="身份证人像面照片" prop="id_card_copy">
            <el-input  readonly v-model="form.id_card_copy" placeholder="身份证人像面照片。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadid_card_CopyUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.id_card_copy" :src="form.id_card_copy_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>

          <el-form-item label="身份证国徽面照片" prop="id_card_national">
            <el-input  readonly v-model="form.id_card_national" placeholder="身份证国徽面照片。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadid_card_nationalUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.id_card_national" :src="form.id_card_national_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>

          <el-form-item label="身份证姓名(必填)" prop="id_card_name">
            <el-input v-model="form.id_card_name" placeholder="经营者/法定代表人对应身份证的姓名，2~30个中文字符、英文字符、符号" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>请填写经营者/法定代表人对应身份证的号码。2、15位数字或17位数字+1位数字|X</span>
          </el-row>
          <el-form-item label="身份证号码(必填)" prop="id_card_number">
            <el-input v-model="form.id_card_number" placeholder="身份证号码" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、请填写身份证有效期的结束时间，注意参照示例中的格式。2、若证件有效期为长期，请填写：长期。3、证件有效期需大于60天。示例值：2026-06-06或者长期</span>
          </el-row>
          <el-form-item label="身份证有效期限(必填)" prop="id_card_valid_time">
            <el-input v-model="form.id_card_valid_time" placeholder="身份证有效期限" />
          </el-form-item>


        </el-card>

        <el-card class="box-card" v-show="form.id_doc_type!='IDENTIFICATION_TYPE_MAINLAND_IDCARD'">
          <h3>经营者/法人其他类型证件信息(选填)</h3>

          <el-form-item label="证件姓名(必填)" prop="id_doc_name">
            <el-input v-model="form.id_doc_name" placeholder="请填写经营者/法人姓名" />
          </el-form-item>


          <el-form-item label="证件号码(必填)" prop="id_doc_number">
            <el-input v-model="form.id_doc_number" placeholder="7~11位 数字|字母|连字符 " />
          </el-form-item>

          <el-form-item label="证件照片(必填)" prop="id_doc_copy">
            <el-input v-model="form.id_doc_copy"  placeholder="请上传证件照片。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadid_doc_copyUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.id_doc_copy" :src="form.id_doc_copy_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>2、若证件有效期为长期，请填写：长期。3、证件有效期需大于60天 。示例值：2020-01-02。</span>
          </el-row>
          <el-form-item label="证件结束日期(必填)" prop="doc_period_end">
            <el-input v-model="form.doc_period_end" placeholder="请填写证件结束日期" />
          </el-form-item>

        </el-card>

        <el-form-item label="是否填写结算账户信息" prop="need_account_info">
          <el-radio-group v-model="form.need_account_info">
            <el-radio :label="true">是</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-card class="box-card">
          <h3>结算账户信息</h3>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、若主体为企业/党政、机关及事业单位/其他组织，可填写：74-对公账户。2、若主体为小微，可填写：75-对私账户。3、若主体为个体工商户，可填写：74-对公账户或75-对私账户。</span>
          </el-row>
          <el-form-item label="账户类型" prop="bank_account_type">
            <el-select v-model="form.bank_account_type" placeholder="账户类型" clearable size="small">
              <el-option v-for="(key, value) in bankAccountType" :key="key" :label="key" :value="value" />
            </el-select>

          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、选择经营者个人银行卡时，开户名称必须与身份证姓名一致。2、选择对公账户时，开户名称必须与营业执照上的“商户名称”一致。</span>
            <el-link :href="'https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml'"  target="_blank">点击开户银行对照表查看</el-link>
          </el-row>
          <el-form-item label="开户银行(必填)" prop="account_bank">
            <el-input v-model="form.account_bank" placeholder="开户银行" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、选择经营者个人银行卡时，开户名称必须与身份证姓名一致。2、选择对公账户时，开户名称必须与营业执照上的“商户名称”一致。</span>
          </el-row>
          <el-form-item label="开户名称(必填)" prop="account_name">
            <el-input v-model="form.account_name" placeholder="开户名称" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>至少精确到市</span>
            <el-link :href="'https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml'"  target="_blank">点击省市区编号对照表查看</el-link>
          </el-row>
          <el-form-item label="开户银行省市编码(必填)" prop="bank_address_code">
            <el-input v-model="form.bank_address_code" placeholder="示例:110000" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一"</span>
            <el-link :href="'https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml'"  target="_blank">开户银行全称（含支行）对照表</el-link>
          </el-row>
          <el-form-item label="开户银行联行号(条件选填)" prop="bank_branch_id">
            <el-input v-model="form.bank_branch_id" placeholder="示例值：402713354941" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、17家直连银行无需填写，如为其他银行，开户银行全称（含支行）和开户银行联行号二选一。</span>
            <el-link :href="'https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml'"  target="_blank">开户银行全称（含支行）对照表</el-link>

          </el-row>
          <el-form-item label="开户银行全称(含支行)(条件选填)" prop="bank_name">
            <el-input v-model="form.bank_name" placeholder="示例值：施秉县农村信用合作联社城关信用社" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>
             <el-link :href="'https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/applyments/chapter4_1.shtml'"  target="_blank">对公/对私卡号长度要求表。</el-link>
            </span>

          </el-row>
          <el-form-item label="银行帐号(必填)" prop="account_number">
            <el-input v-model="form.account_number" />
          </el-form-item>

        </el-card>

        <el-card class="box-card">
          <h3>超级管理员信息</h3>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、小微商户，选择：65-法人/经营者。2、个体工商户/企业/党政、机关及事业单位/其他组织，可选择：65-法人/经营者、66- 负责人。 （负责人：经商户授权办理微信支付业务的人员，授权范围包括但不限于签约，入驻过程需完成账户验证）。</span>
          </el-row>
          <el-form-item label="超级管理员类型(必填)" prop="bank_account_type">
            <el-select v-model="form.bank_account_type" placeholder="超级管理员类型" clearable size="small">
              <el-option v-for="(key, value) in contactType" :key="key" :label="key" :value="value" />
            </el-select>
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、若管理员类型为“法人”，则该姓名需与法人身份证姓名一致。2、若管理员类型为“经办人”，则可填写实际经办人的姓名。</span>
          </el-row>
          <el-form-item label="超级管理员姓名(必填)" prop="contact_type">
            <el-input v-model="form.contact_type" placeholder="特殊规则：长度最小15个字节" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、若管理员类型为法人，则该身份证号码需与法人身份证号码一致。若管理员类型为经办人，则可填写实际经办人的身份证号码。2、可传身份证、来往内地通行证、来往大陆通行证、护照等证件号码。3、超级管理员签约时，校验微信号绑定的银行卡实名信息，是否与该证件号码一致。</span>
          </el-row>
          <el-form-item label="超级管理员身份证件号码(必填)" prop="contact_id_card_number">
            <el-input  readonly v-model="form.contact_id_card_number" placeholder="超级管理员身份证件号码" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadcontact_id_card_numberUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.contact_id_card_number" :src="form.contact_id_card_number_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、请填写管理员的手机号，11位数字， 用于接收微信支付的重要管理信息及日常操作验证码 。</span>
          </el-row>
          <el-form-item label="超级管理员手机(必填)" prop="mobile_phone">
            <el-input v-model="form.mobile_phone" placeholder="超级管理员手机" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、用于接收微信支付的开户邮件及日常业务通知。2、需要带@，遵循邮箱格式校验 </span>
          </el-row>
          <el-form-item label="超级管理员邮箱(必填)" prop="contact_email">
            <el-input v-model="form.contact_email" placeholder="超级管理员邮箱" />
          </el-form-item>


        </el-card>

        <el-card class="box-card">
          <h3>店铺信息</h3>

          <el-form-item label="店铺名称(必填)" prop="store_name">
            <el-input v-model="form.store_name" placeholder="示例值：爱烧烤" />
          </el-form-item>

          <el-row :gutter="10" type="flex" align="middle" style="padding:10px 0;">
            <span style="color: #1ab394"><label >备注:&nbsp;&nbsp;</label>1、店铺二维码or店铺链接二选一必填。2、请填写店铺主页链接，需符合网站规范。示例值：http://www.qq.com。</span>
          </el-row>

          <el-form-item label="二选一">
            <el-radio-group v-model="form.isQrCode">
              <el-radio :label="true">店铺二维码</el-radio>
              <el-radio :label="false">店铺链接</el-radio>
            </el-radio-group>
          </el-form-item>

          <el-form-item label="店铺链接"  v-show="!form.isQrCode">
            <el-input v-model="form.store_url" placeholder="示例值：http://www.qq.com" />
          </el-form-item>

          <el-form-item label="店铺二维码(必填)" v-show="form.isQrCode">
            <el-input    v-model="form.store_qr_code" placeholder="店铺二维码(必填)。" />
            <el-upload
              :action="uploadPathwx"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadstore_qr_codeUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="form.store_qr_code" :src="form.store_qr_code_url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon" />
            </el-upload>

          </el-form-item>
        </el-card>

        <el-form-item label="商户简称(必填)" prop="merchant_shortname">
          <el-input v-model="form.merchant_shortname" placeholder="将在支付完成页向买家展示，需与商家的实际售卖商品相符" />
        </el-form-item>

        <el-form-item label="特殊资质(非必填)" prop="qualifications">
          <el-input v-model="form.qualifications"  readonly placeholder="若店铺业务包含互联网售药，则需上传特殊资质-《互联网药品交易服务证》,最多可上传5张照片" />     <el-upload
          :action="uploadPathwx"
          :limit="5"
          :headers="headers"
          :on-exceed="uploadOverrun"
          :on-success="handleQualificationsUrl"
          :on-remove="handleQualificationsRemove"
          multiple
          accept=".jpg,.jpeg,.png,.gif"
          list-type="picture-card"
        >
          <i class="el-icon-plus" />
        </el-upload>
        </el-form-item>

        <el-form-item label="补充材料(非必填)" prop="business_addition_pics">
          <el-input v-model="form.business_addition_pics"  readonly placeholder="最多可上传5张照片，请填写通过图片上传接口预先上传图片生成好的MediaID" />
          <el-upload
            :action="uploadPathwx"
            :limit="5"
            :headers="headers"
            :on-exceed="uploadOverrun"
            :on-success="handleAdditionPicsUrl"
            :on-remove="handleAdditionPicsRemove"
            multiple
            accept=".jpg,.jpeg,.png,.gif"
            list-type="picture-card"
          >
            <i class="el-icon-plus" />
          </el-upload>
        </el-form-item>

        <el-form-item label="补充说明(非必填)" prop="business_addition_desc">
          <el-input v-model="form.business_addition_desc" placeholder="特殊情况，说明原因最多512字节" />
        </el-form-item>
      </el-form>
    </el-card>

    <div class="op-container">
      <el-button @click="handleCancel">重置</el-button>
      <el-button type="primary" @click="handlePublish">提交进件</el-button>
    </div>

  </div>
</template>

<style>
  .el-card {
    margin-bottom: 10px;
  }

  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .input-new-keyword {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .avatar-uploader .el-upload {
    width: 145px;
    height: 145px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }

  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
  .op-container {
    display: flex;
    justify-content: center;

  }
</style>

<script>
import { merchantApplaymentWx} from '@/api/shop/merchant'

import { createStorage, uploadPathwx,uploadPath } from '@/api/shop/storage'
import { getToken } from '@/utils/auth'


const idDocType = {
  'IDENTIFICATION_TYPE_MAINLAND_IDCARD':'中国大陆居民-身份证',
  'IDENTIFICATION_TYPE_OVERSEA_PASSPORT':'其他国家或地区居民-护照',
  'IDENTIFICATION_TYPE_HONGKONG':'中国香港居民–来往内地通行证',
  'IDENTIFICATION_TYPE_MACAO':'中国澳门居民–来往内地通行证',
  'IDENTIFICATION_TYPE_TAIWAN':'中国台湾居民–来往大陆通行证'
}

const bankAccountType={
  '74':'对公账户',
  '75':'对私用户'
}


const contactType={
  '65':'法人/经营者',
  '66':'负责人'
}

const isNum = (rule, value, callback) => {
  const data= /^[0-9]*$/
  if (!data.test(value)) {
    callback(new Error('只能为数字'))
  }else{
    callback()
  }
}

export default {
  name: 'merchant',
  data() {
    return {
      uploadPathwx,
      uploadPath,
      organizationTypes: [],
      idDocType,
      bankAccountType,
      contactType,
      need_account_info: true,
      qualifications: [],
      business_addition_pics: [],
      isQrCode: false,
      // merchant: { organization_type: '', id_doc_type: '',business_addition_desc: '',need_account_info: true,qualifications: [], business_addition_pics: [],
      //   businessEndTime: false, isQrCode: false, mediaId: '' },
      // business_license_info:{business_license_copy: '', business_license_number: '', merchant_name: '', legal_person: '',company_address: '',business_time_start: '',business_time_end: ''},
      // organization_cert_info:{organization_copy: '',organization_number:'',organization_time: ''},
      // id_card_info:{id_card_copy: '',id_card_national: '',id_card_name: '',id_card_number: '',id_card_valid_time: ''},
      // id_doc_info:{id_doc_name: '',id_doc_number: '',id_doc_copy: '',doc_period_end: ''},
      // account_info:{bank_account_type: '',account_bank: '',account_name: '',bank_address_code: '',bank_branch_id: '',bank_name: '',account_number: ''},
      // contact_info:{contact_type: '',contact_name: '',contact_id_card_number: '',mobile_phone: '',contact_email: ''},
      // sales_scene_info:{store_name: '',store_url: '',store_qr_code: ''},
    form: { need_account_info: true, isQrCode: false,isLongTime:false,id_doc_type: 'IDENTIFICATION_TYPE_MAINLAND_IDCARD',business_license_copy:'', business_license_number: '', merchant_name: '', legal_person: '',company_address: '',business_time_start: '',
      business_time_end: '',organization_copy: '',
      organization_number: '',
      organization_time: '',
      id_card_copy: '',
      id_card_national: '',
      id_card_name: '',
      id_card_number: '',
      id_card_valid_time: '',
      id_doc_name: '',
      id_doc_number: '',
      id_doc_copy: '',
      doc_period_end: '',
      bank_account_type: '',
      account_bank: '',
      account_name: '',
      bank_address_code: '',
      bank_branch_id: '',
      bank_name: '',
      account_number: '',
      contact_type: '',
      contact_name: '',
      contact_id_card_number: '',
      mobile_phone: '',
      contact_email: '',
      store_name: '',
      store_url: '',
      store_qr_code: ''

    },
      // business_license_info:{},
      // organization_cert_info:{},
      // id_card_info:{},
      // id_doc_info:{},
      // account_info:{},
      // contact_info:{},
      // sales_scene_info:{},

      businessrules:{
        business_license_copy: [{ required: true, message: '不能空', trigger: 'blur' }],
        business_license_number: [{ required: true, message: '不能空', trigger: 'blur' }],
        merchant_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        legal_person: [{ required: true, message: '不能空', trigger: 'blur' }],
        company_address: [{ required: true, message: '不能空', trigger: 'blur' }],
        business_time_start:[{ required: true, message: '不能空', trigger: 'blur' }],
      },

      organizationrules:{
        organization_copy: [{ required: true, message: '不能空', trigger: 'blur' }],
        organization_number: [{ required: true, message: '不能空', trigger: 'blur' }],
        organization_time: [{ required: true, message: '不能空', trigger: 'blur' }],

      },

      id_cardrules:{
        id_card_copy: [{ required: true, message: '不能空', trigger: 'blur' }],
        id_card_national: [{ required: true, message: '不能空', trigger: 'blur' }],
        id_card_name: [{ required: true, max:30,message: '不能空,2~30个中文字符、英文字符、符号', trigger: 'blur' }],
        id_card_number: [{ required: true, message: '不能空', trigger: 'blur' }],
        id_card_valid_time: [{ required: true, message: '不能空', trigger: 'blur' }],

      },


      id_docrules:{
        id_doc_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        id_doc_number: [{ required: true, max: 11,pattern:/(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,16}/,message: '7~11位 数字|字母|连字符', trigger: 'blur' }],
        id_doc_copy: [{ required: true, message: '不能空', trigger: 'blur' }],
        doc_period_end: [{ required: true, message: '不能空', trigger: 'blur' }],

      },

      store_urlrules:{
        store_url: [{ required: true, message: '不能空', trigger: 'blur' }],
      },
      store_qr_coderules:{
        store_qr_code: [{ required: true, message: '不能空', trigger: 'blur' }],
      },
      initrules: {
        organization_type: [{ required: true, message: '主体类型不能空', trigger: 'blur' }],
        //
        // need_account_info: [{ required: true, message: '不能空', trigger: 'blur' }],
        //
        // contact_type: [{ required: true, message: '不能空', trigger: 'blur' }],
        // contact_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        // contact_id_card_number: [{ required: true, message: '不能空', trigger: 'blur' }],
        // mobile_phone: [{ required: true, pattern: /^1[3-9]\d{9}$/, message: '手机号格式错误', trigger: 'blur'}],
        // contact_email: [{ required: true, pattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/, message: '邮箱格式错误', trigger: 'blur' }],
        //
        // bank_account_type: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_bank: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        // bank_address_code: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_number: [{ required: true,pattern: /^[0-9]*$/,message: '只能为数字', trigger: 'blur' }],
        //
        //
        // store_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        //
        //
        //
        // merchant_shortname: [{ required: true, message: '不能空', trigger: 'blur' }],

      },

      rules: {
        organization_type: [{ required: true, message: '主体类型不能空', trigger: 'blur' }],
        //
        // need_account_info: [{ required: true, message: '不能空', trigger: 'blur' }],
        //
        // contact_type: [{ required: true, message: '不能空', trigger: 'blur' }],
        // contact_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        // contact_id_card_number: [{ required: true, message: '不能空', trigger: 'blur' }],
        // mobile_phone: [{ required: true, pattern: /^1[3-9]\d{9}$/, message: '手机号格式错误', trigger: 'blur'}],
        // contact_email: [{ required: true, pattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/, message: '邮箱格式错误', trigger: 'blur' }],
        //
        // bank_account_type: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_bank: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        // bank_address_code: [{ required: true, message: '不能空', trigger: 'blur' }],
        // account_number: [{ required: true,pattern: /^[0-9]*$/,message: '只能为数字', trigger: 'blur' }],
        //
        //
        // store_name: [{ required: true, message: '不能空', trigger: 'blur' }],
        //
        //
        //
        // merchant_shortname: [{ required: true, message: '不能空', trigger: 'blur' }],

      },

      rulesex:{}

    }
  },
  computed: {
    headers() {
      return {
        headers: {Authorization : 'Bearer ' + getToken()},
      }
    },

  },
  created() {

    this.getDicts("merchant_organization_type").then(response => {
      this.organizationTypes = response.data;
    });
  },

  methods: {
    timeChange: function () {
      if (this.form.isLongTime) {
        this.form.business_time_end = '长期'
      }
    },

    getOrganization_type:function(){

      if (this.form.organization_type=='2401'){
        this.form.business_license_copy='';
      }

    },

    reset(){
      this.form = {
        organization_type: undefined,
        id_doc_type: undefined,
        business_addition_desc: undefined,
        need_account_info: true,
        qualifications: undefined,
        business_addition_pics: undefined,
        businessEndTime: false,
        isQrCode: false,
        mediaId: undefined,
        business_license_copy: undefined,
        business_license_number: undefined,
        merchant_name: undefined,
        legal_person: undefined,
        company_address: undefined,
        business_time_start: undefined,
        business_time_end: undefined,
        organization_copy: undefined,
        organization_number: undefined,
        organization_time: undefined,
        id_card_copy: undefined,
        id_card_national: undefined,
        id_card_name: undefined,
        id_card_number: undefined,
        id_card_valid_time: undefined,
        id_doc_name: undefined,
        id_doc_number: undefined,
        id_doc_copy: undefined,
        doc_period_end: undefined,
        bank_account_type: undefined,
        account_bank: undefined,
        account_name: undefined,
        bank_address_code: undefined,
        bank_branch_id: undefined,
        bank_name: undefined,
        account_number: undefined,
        contact_type: undefined,
        contact_name: undefined,
        contact_id_card_number: undefined,
        mobile_phone: undefined,
        contact_email: undefined,
        store_name: undefined,
        store_url: undefined,
        store_qr_code: undefined
      }
    },




    // 表单重置
    handleCancel: function () {
      this.reset();
      this.$router.push({path: '/merchant/applyment'})
    },
    handlePublish: function () {
      // this.$nextTick(() => {
      //   this.$refs['form'].resetFields()
      // })
     this.rules=this.initrules;
      this.rulesex={};

      this.$refs["form"].validate(valid => {
        if (valid) {

         if (this.form.organization_type!='2401') {
           this.rulesex= Object.assign({}, this.rulesex, this.businessrules)
         }
         if (this.form.business_license_number.length<18) {
           this.rulesex= Object.assign({}, this.rulesex, this.organizationrules)
         }
         if (this.form.id_doc_type=='IDENTIFICATION_TYPE_MAINLAND_IDCARD') {
           this.rulesex= Object.assign({}, this.rulesex, this.id_cardrules)
         }else {

           this.rulesex= Object.assign({}, this.rulesex, this.id_docrules)
         }
         if (this.form.isQrCode) {
           this.rulesex= Object.assign({}, this.rulesex, this.store_qr_coderules)
         }else {
           this.rulesex= Object.assign({}, this.rulesex, this.store_urlrules)
         }
          this.rules= Object.assign({}, this.rules, this.rulesex)

          this.$refs["form"].validate(valid => {
            if (valid) {

              console.log(this.form)

            }})
        } else {
          console.log('error submit!!')
          return false
        }

      })
          // const finalGoods = {
      //   goods: this.goods,
      //   specifications: this.specifications,
      //   products: this.products,
      //   attributes: this.attributes
      // }
      // publishGoods(finalGoods).then(response => {
      //
      //   if (response.code === 200) {
      //     this.msgSuccess("新增成功");
      //     this.reset();
      //     this.$router.push({path: '/goods/goods'})
      //   } else {
      //     this.msgError(response.msg);
      //   }
      //
      //
      // })
    },

    uploadPicUrl: function (response) {

      this.form.mediaId = response.data.url
    },
    uploadOverrun: function () {
      this.$message({
        type: 'error',
        message: '上传文件个数超出限制!最多上传5张图片!'
      })
    },


    handleQualificationsUrl(response, file, fileList) {
      if (response.code === 200) {
        this.qualifications.push(response.data.mediaId)
      }
      this.form.qualifications=this.qualifications;
    },
    handleQualificationsRemove: function (file, fileList) {
      for (var i = 0; i < this.qualifications.length; i++) {
        // 这里存在两种情况
        // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
        //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
        // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
        var url
        if (file.response === undefined) {
          url = file.url
        } else {
          url = file.response.data.mediaId
        }

        if (this.qualifications[i] === url) {
          this.qualifications.splice(i, 1)
        }
      }
       this.form.qualifications=this.qualifications;
    },


    handleAdditionPicsUrl(response, file, fileList) {
      if (response.code === 200) {
        this.form.business_addition_pics.push(response.data.mediaId)
      }
      this.form.business_addition_pics=this.business_addition_pics;
    },
    handleAdditionPicsRemove: function (file, fileList) {
      for (var i = 0; i < this.form.business_addition_pics.length; i++) {
        // 这里存在两种情况
        // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
        //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
        // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
        var url
        if (file.response === undefined) {
          url = file.mediaId
        } else {
          url = file.response.data.mediaId
        }

        if (this.form.business_addition_pics[i] === url) {
          this.form.business_addition_pics.splice(i, 1)
        }
      }
      this.form.business_addition_pics=this.business_addition_pics;
    },


    removeBusinessLicenseCopyUrl: function (file) {
      var url
      if (file.response === undefined) {
        url = file.url
      } else {
        url = file.response.data.url
      }

      if (this.form.business_license_copy === url) {
        this.form.business_license_copy.splice(i, 1)
      }
    },






    uploadBusinessLicenseCopyUrl: function (response) {
      if (this.form.organization_type===undefined) {
       return this.$message({
          type: 'error',
          message: '请先选择营业主体'
        })
      }
      this.form.business_license_copy_url = response.data.url
      this.form.business_license_copy = response.data.mediaId
    },


    uploadorganization_CopyUrl: function (response) {
      this.form.organization_copy_url = response.data.url
      this.form.organization_copy = response.data.mediaId
    },

    uploadid_card_CopyUrl: function (response) {
      this.form.id_card_copy_url = response.data.url
      this.form.id_card_copy = response.data.mediaId
    },

    uploadid_card_nationalUrl: function (response) {
      this.form.id_card_national_url = response.data.url
      this.form.id_card_national = response.data.mediaId
    },

    uploadid_doc_copyUrl: function (response) {
      this.form.id_doc_copy_url = response.data.url
      this.form.id_doc_copy = response.data.mediaId
    },

    uploadcontact_id_card_numberUrl: function (response) {
      this.form.contact_id_card_number_url = response.data.url
      this.form.contact_id_card_number = response.data.mediaId
    },

    uploadstore_qr_codeUrl: function (response) {
      this.form.store_qr_code_url = response.data.url
      this.form.store_qr_code = response.data.mediaId
    },


  }

}
</script>
