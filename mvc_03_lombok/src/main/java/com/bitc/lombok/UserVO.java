package com.bitc.lombok;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

// @Data
@Getter
@Setter
// @ToString(exclude="upw")
@ToString(of= {"uid", "upw", "uname"})
// @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"uid", "upw"})
@RequiredArgsConstructor
@Builder
public class UserVO {
	
	@Getter
	private int uno;
	@Setter
	@NonNull
	private String uid;
	@NonNull
	private String upw;
	private final String uname;
	private Date regdate;
	
	@Singular("list")
	private List<String> friends;
	
}
